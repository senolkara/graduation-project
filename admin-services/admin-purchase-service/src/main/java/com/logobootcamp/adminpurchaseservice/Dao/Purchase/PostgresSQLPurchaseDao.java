package com.logobootcamp.adminpurchaseservice.Dao.Purchase;

import com.logobootcamp.commonservice.Dto.Buying.BuyingDto;
import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.commonservice.Dto.Purchase.PurchaseDto;
import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.commonservice.Dto.Traveller.TravellerDto;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.adminpurchaseservice.Client.IAccountServiceClient;
import com.logobootcamp.adminpurchaseservice.Client.IPaymentServiceClient;
import com.logobootcamp.adminpurchaseservice.Client.ITravelServiceClient;
import com.logobootcamp.adminpurchaseservice.Entities.Purchase.Purchase;
import com.logobootcamp.adminpurchaseservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PostgresSQLPurchaseDao implements IPurchaseDao {

    private final IPurchaseRepository iPurchaseRepository;
    private final ModelMapper modelMapper;
    private final IAccountServiceClient iAccountServiceClient;
    private final ITravelServiceClient iTravelServiceClient;
    private final IPaymentServiceClient iPaymentServiceClient;

    /**
     * bütün satınalmaları listele
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
        List<Purchase> purchaseList = this.iPurchaseRepository.findAll();
        for (Purchase purchase:purchaseList){
            LoggedUserDto loggedUserDto = this.loggedUserControl(purchase.getUserId());
            UserDto userDto = loggedUserDto.getUserDto();
            BuyingDto buyingDto = new BuyingDto();
            PurchaseDto purchaseDto = PurchaseDto.builder()
                    .travelDto(this.travelControl(purchase.getTravelId(), loggedUserId))
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
     * satınalma detayı
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
        PurchaseDto purchaseDto = PurchaseDto.builder()
                .userDto(userDto)
                .travelDto(this.travelControl(purchase.getTravelId(), loggedUserId))
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
     * satınalma iptali
     *
     * @param id
     * @param loggedUserId
     * @throws GeneralException
     */
    @Override
    public void deletePurchase(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Purchase purchase = this.iPurchaseRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("purchase not found with id: " + id));
        this.iPaymentServiceClient.deletePayment(purchase.getPaymentId(), loggedUserId);
    }

    /**
     * seyahat(sefer) bilgisinin travel service den kontrolü
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    public TravelDto travelControl(Long id, Long loggedUserId) throws GeneralException {
        TravelDto travelDto = this.iTravelServiceClient.getTravel(id, loggedUserId).getBody();
        if (travelDto == null){
            throw new GeneralException("travel not found with id: " + id);
        }
        return travelDto;
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
        if (loggedUserDto.getUserDto().getUserType() != UserType.ADMIN){
            throw new GeneralException("cannot use this service because user is not admin");
        }
        return loggedUserDto;
    }
}
