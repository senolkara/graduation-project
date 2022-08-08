package com.logobootcamp.otherpurchaseservice.Dao.Purchase;

import com.logobootcamp.commonservice.Dto.Buying.BuyingDto;
import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.Notification.NotificationDto;
import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.commonservice.Needs.Enums.GenderType;
import com.logobootcamp.commonservice.Requests.BuyingRequest;
import com.logobootcamp.commonservice.Requests.TravelRequest;
import com.logobootcamp.otherpurchaseservice.Client.IAccountServiceClient;
import com.logobootcamp.otherpurchaseservice.Client.IPaymentServiceClient;
import com.logobootcamp.commonservice.Dto.Purchase.PurchaseDto;
import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.commonservice.Dto.Traveller.TravellerDto;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.otherpurchaseservice.Client.IUserServiceClient;
import com.logobootcamp.otherpurchaseservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import com.logobootcamp.commonservice.Requests.PurchaseRequest;
import com.logobootcamp.otherpurchaseservice.Client.ITravelServiceClient;
import com.logobootcamp.otherpurchaseservice.Dao.Traveller.ITravellerRepository;
import com.logobootcamp.otherpurchaseservice.Entities.Purchase.Purchase;
import com.logobootcamp.otherpurchaseservice.Entities.Traveller.Traveller;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Primary
public class MySQLPurchaseDao implements IPurchaseDao {

    private final IPurchaseRepository iPurchaseRepository;
    private final ITravellerRepository iTravellerRepository;
    private final ModelMapper modelMapper;
    private final IAccountServiceClient iAccountServiceClient;
    private final IUserServiceClient iUserServiceClient;
    private final ITravelServiceClient iTravelServiceClient;
    private final IPaymentServiceClient iPaymentServiceClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.routing.name}")
    private String routingName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    /**
     * bireysel veya kurumsal kullanıcıların yapmış oldukları satınalmalar
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public List<BuyingDto> getAllPurchase(Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Map<Long, Double> paymentMap = new HashMap<>();
        List<BuyingDto> buyingDtoList = new ArrayList<>();
        List<PurchaseDto> purchaseDtoList = new ArrayList<>();
        List<Purchase> purchaseList = this.iPurchaseRepository.findAllByUserId(loggedUserId);
        for (Purchase purchase:purchaseList){
            LoggedUserDto loggedUserDto = this.loggedUserControl(purchase.getUserId());
            UserDto userDto = loggedUserDto.getUserDto();
            TravelDto travelDto = this.iTravelServiceClient.getTravel(purchase.getTravelId(), loggedUserId).getBody();
            BuyingDto buyingDto = new BuyingDto();
            PurchaseDto purchaseDto = PurchaseDto.builder()
                    .travelDto(travelDto)
                    .travellerDto(modelMapper.map(purchase.getTraveller(), TravellerDto.class))
                    .userDto(userDto)
                    .build();
            purchaseDtoList.add(purchaseDto);
            buyingDto.setPurchaseDtoList(purchaseDtoList);
            if (!paymentMap.containsKey(purchase.getPaymentId())){
                PaymentDto paymentDto = this.iPaymentServiceClient.getPayment(purchase.getPaymentId(), loggedUserId).getBody();
                buyingDto.setPaymentDto(paymentDto);
            }
            buyingDtoList.add(buyingDto);
        }
        return buyingDtoList;
    }

    /**
     * bireysel veya kurumsal kullanıcıların yapmış oldukları satınalmanın detayı
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public BuyingDto getPurchase(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        List<PurchaseDto> purchaseDtoList = new ArrayList<>();
        Purchase purchase = this.iPurchaseRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("purchase not found with id: " + id));
        LoggedUserDto loggedUserDto = this.loggedUserControl(purchase.getUserId());
        UserDto userDto = loggedUserDto.getUserDto();
        TravelDto travelDto = this.iTravelServiceClient.getTravel(purchase.getTravelId(), loggedUserId).getBody();
        PurchaseDto purchaseDto = PurchaseDto.builder()
                .userDto(userDto)
                .travelDto(travelDto)
                .travellerDto(modelMapper.map(purchase.getTraveller(), TravellerDto.class))
                .build();
        purchaseDtoList.add(purchaseDto);
        PaymentDto paymentDto = this.iPaymentServiceClient.getPayment(purchase.getPaymentId(), loggedUserId).getBody();
        BuyingDto buyingDto = BuyingDto.builder()
                .purchaseDtoList(purchaseDtoList)
                .paymentDto(paymentDto)
                .build();
        return buyingDto;
    }

    /**
     * bireysel veya kurumsal kullanıcıların satınalma gerçekleştirmesi
     *
     * senkron olarak gerçekleştirilen satınalmanın sonucunda
     * asenkron kuyruk yapılandırmasıyla satınalma ve ödeme detaylarının
     * kuyruğa iletilmesi yapılmıştır.
     *
     * @param buyingRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public BuyingDto createPurchase(BuyingRequest buyingRequest, Long loggedUserId) throws GeneralException {
        this.allControls(buyingRequest, loggedUserId);
        PaymentDto paymentDto = this.iPaymentServiceClient.createPayment(buyingRequest, loggedUserId).getBody();
        List<PurchaseDto> purchaseDtoList = new ArrayList<>();
        for (PurchaseRequest purchaseRequest: buyingRequest.getPurchaseRequests()){
            UserDto userDto = this.iUserServiceClient.getUser(loggedUserId).getBody();
            TravelDto travelDto = this.iTravelServiceClient.getTravel(purchaseRequest.getTravelId(), loggedUserId).getBody();
            TravellerDto travellerDto = modelMapper.map(purchaseRequest.getTravellerRequest(), TravellerDto.class);
            PurchaseDto purchaseDto = PurchaseDto.builder()
                    .travelDto(travelDto)
                    .travellerDto(travellerDto)
                    .userDto(userDto)
                    .build();
            Traveller traveller = this.iTravellerRepository.save(modelMapper.map(travellerDto, Traveller.class));
            Purchase purchase = modelMapper.map(purchaseRequest, Purchase.class);
            purchase.setUserId(loggedUserId);
            purchase.setTraveller(traveller);
            purchase.setPaymentId(paymentDto.getId());
            this.iPurchaseRepository.save(purchase);
            purchaseDtoList.add(purchaseDto);
            TravelRequest travelRequest = TravelRequest.builder()
                    .provinceFrom(travelDto.getProvinceFrom().getId())
                    .provinceWhere(travelDto.getProvinceWhere().getId())
                    .amount(travelDto.getAmount())
                    .date(travelDto.getDate())
                    .capacity(travelDto.getCapacity() - 1)
                    .vehicleType(travelDto.getVehicleType())
                    .statusType(travelDto.getStatusType())
                    .build();
            this.iTravelServiceClient.updateTravel(purchase.getTravelId(), travelRequest, loggedUserId);
        }
        LoggedUserDto loggedUserDto = this.loggedUserControl(loggedUserId);
        UserDto userDto = loggedUserDto.getUserDto();
        NotificationDto notificationDto = NotificationDto.builder()
                .userDto(userDto)
                .purchaseDtoList(purchaseDtoList)
                .build();
        this.rabbitTemplate.convertAndSend(exchangeName, routingName, notificationDto);
        BuyingDto buyingDto = BuyingDto.builder()
                .purchaseDtoList(purchaseDtoList)
                .paymentDto(paymentDto)
                .build();
        return buyingDto;
    }

    public void allControls(BuyingRequest buyingRequest, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        this.userTravelCountControl(buyingRequest, loggedUserId);
        this.countPurchaseGenderType(buyingRequest);
        this.travelOtherControl(buyingRequest, loggedUserId);
    }

    /**
     * tek bir satınalma da en fazla 2 erkek yolcu için alım yapılıyor mu kontrolü
     *
     * @param buyingRequest
     * @throws GeneralException
     */
    public void countPurchaseGenderType(BuyingRequest buyingRequest) throws GeneralException {
        int countPurchaseGenderType = 0;
        for (PurchaseRequest purchaseRequest: buyingRequest.getPurchaseRequests()){
            if (purchaseRequest.getTravellerRequest().getGenderType() == GenderType.MALE){
                countPurchaseGenderType++;
            }
            if (countPurchaseGenderType > 2){
                throw new GeneralException("Ticket for up to 2 male travellers in a single order can take");
            }
        }
    }

    /**
     * seyahat(sefer) işlemleri için, aktif-pasif,
     * kapasite, seyahat tarihi sona ermiş vs. gibi kontroller
     *
     * @param buyingRequest
     * @param loggedUserId
     * @throws GeneralException
     */
    public void travelOtherControl(BuyingRequest buyingRequest, Long loggedUserId) throws GeneralException {
        Map<Long, Integer> userIdAndTravelIdCountHashMap = this.getUserIdAndTravelIdCount(buyingRequest, loggedUserId).get(loggedUserId);
        for (PurchaseRequest purchaseRequest: buyingRequest.getPurchaseRequests()){
            TravelDto travelDto = this.iTravelServiceClient.getTravel(purchaseRequest.getTravelId(), loggedUserId).getBody();
            if (travelDto.getStatusType() == StatusType.PASSIVE){
                throw new GeneralException("travel is passive");
            }
            if ((travelDto.getCapacity() <= 0) || (travelDto.getCapacity() - userIdAndTravelIdCountHashMap.get(purchaseRequest.getTravelId()) <= 0)){
                throw new GeneralException("travel capacity is full or insufficient capacity");
            }
            if (travelDto.getDate().before(new Date(System.currentTimeMillis()))){
                throw new GeneralException("travel date is before now date");
            }
        }
    }

    /**
     * bireysel kullanıcının aynı sefer için en fazla 5 bilet alması,
     * kurumsal kullanıcı aynı sefer için en fazla 20 bilet alması kontrolü
     *
     * @param buyingRequest
     * @param loggedUserId
     * @throws GeneralException
     */
    public void userTravelCountControl(BuyingRequest buyingRequest, Long loggedUserId) throws GeneralException {
        LoggedUserDto loggedUserDto = this.loggedUserControl(loggedUserId);
        UserDto userDto = loggedUserDto.getUserDto();
        for (PurchaseRequest purchaseRequest: buyingRequest.getPurchaseRequests()){
            int userIdAndTravelIdCount = this.getUserIdAndTravelIdCount(buyingRequest, loggedUserId).get(loggedUserId).get(purchaseRequest.getTravelId());
            long userTravelCount = this.iPurchaseRepository.countAllByUserIdAndTravelId(loggedUserId, purchaseRequest.getTravelId()) + userIdAndTravelIdCount;
            if (userTravelCount > 5 && userDto.getUserType() == UserType.PERSONAL){
                throw new GeneralException("A maximum of 5 tickets can be purchased for personal user: " + loggedUserId + " and travel: " + purchaseRequest.getTravelId());
            }
            if (userTravelCount > 20 && userDto.getUserType() == UserType.CORPORATE){
                throw new GeneralException("A maximum of 20 tickets can be purchased for corporate user: " + loggedUserId + " and travel: " + purchaseRequest.getTravelId());
            }
        }
    }

    /**
     * bireysel veya kurumsal kullanıcıların kaç seyahat(sefer) için satınalma isteğinde bulunduklarının kontrolü
     *
     * @param buyingRequest
     * @param loggedUserId
     * @return
     */
    public Map<Long, Map<Long, Integer>> getUserIdAndTravelIdCount(BuyingRequest buyingRequest, Long loggedUserId){
        Map<Long, Map<Long, Integer>> userTravelCountHashMap = new HashMap<>();
        Map<Long, Integer> travelCountHashMap = new HashMap<>();
        for (PurchaseRequest purchaseRequest: buyingRequest.getPurchaseRequests()){
            if (travelCountHashMap.containsKey(purchaseRequest.getTravelId())){
                travelCountHashMap.put(purchaseRequest.getTravelId(), travelCountHashMap.get(purchaseRequest.getTravelId()) + 1);
            }
            else{
                travelCountHashMap.put(purchaseRequest.getTravelId(), 1);
            }
            userTravelCountHashMap.put(loggedUserId, travelCountHashMap);
        }
        return userTravelCountHashMap;
    }

    /**
     * kullanıcının gün içerisinde giriş yapıp yaptığının kontrolü
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    public LoggedUserDto loggedUserControl(Long loggedUserId) throws GeneralException {
        LoggedUserDto loggedUserDto = this.iAccountServiceClient.loggedUserControl(loggedUserId).getBody();
        if (loggedUserDto == null){
            throw new GeneralException("logged user record not found with id: " + loggedUserId);
        }
        if (loggedUserDto.getUserDto().getUserType() == UserType.ADMIN){
            throw new GeneralException("cannot use this service because user is admin");
        }
        return loggedUserDto;
    }
}
