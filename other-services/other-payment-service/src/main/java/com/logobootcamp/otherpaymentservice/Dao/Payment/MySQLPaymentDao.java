package com.logobootcamp.otherpaymentservice.Dao.Payment;

import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import com.logobootcamp.otherpaymentservice.Client.IAccountServiceClient;
import com.logobootcamp.otherpaymentservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Needs.Enums.PaymentStatusType;
import com.logobootcamp.commonservice.Requests.BuyingRequest;
import com.logobootcamp.commonservice.Requests.PaymentRequest;
import com.logobootcamp.commonservice.Requests.PurchaseRequest;
import com.logobootcamp.otherpaymentservice.Client.ITravelServiceClient;
import com.logobootcamp.otherpaymentservice.Entities.Payment.Payment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
@RequiredArgsConstructor
@Primary
public class MySQLPaymentDao implements IPaymentDao {

    private final IPaymentRepository iPaymentRepository;
    private final IAccountServiceClient iAccountServiceClient;
    private final ITravelServiceClient iTravelServiceClient;
    private final ModelMapper modelMapper;

    @Override
    public PaymentDto getPayment(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Payment payment = this.iPaymentRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("payment not found with id: " + id));
        return modelMapper.map(payment, PaymentDto.class);
    }

    @Override
    public PaymentDto createPayment(BuyingRequest buyingRequest, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        PaymentRequest paymentRequest = buyingRequest.getPaymentRequest();
        this.validDateControl(paymentRequest);
        if (!paymentRequest.getCardInfoSaveOrNot()){
            paymentRequest.setNameOnTheCard(null);
            paymentRequest.setCardNo(null);
            paymentRequest.setValidDate(null);
            paymentRequest.setCvv(null);
        }
        PaymentDto paymentDto = PaymentDto.builder()
                .nameOnTheCard(paymentRequest.getNameOnTheCard())
                .cardNo(paymentRequest.getCardNo())
                .validDate(paymentRequest.getValidDate())
                .cvv(paymentRequest.getCvv())
                .date(new java.sql.Date(System.currentTimeMillis()))
                .totalAmount(this.getTotalAmount(buyingRequest, loggedUserId))
                .paymentType(paymentRequest.getPaymentType())
                .paymentStatusType(PaymentStatusType.COMPLETED)
                .build();
        Payment payment = this.iPaymentRepository.save(modelMapper.map(paymentDto, Payment.class));
        return modelMapper.map(payment, PaymentDto.class);
    }

    public void validDateControl(PaymentRequest paymentRequest) throws GeneralException {
        if (paymentRequest.getValidDate().before(new Date(System.currentTimeMillis()))){
            throw new GeneralException("valid date is before now date");
        }
    }

    public TravelDto travelControl(Long id, Long loggedUserId) throws GeneralException {
        TravelDto travelDto = this.iTravelServiceClient.getTravel(id ,loggedUserId).getBody();
        if (travelDto == null){
            throw new GeneralException("travel not found with id: " + id);
        }
        return travelDto;
    }

    public double getTotalAmount(BuyingRequest buyingRequest, Long loggedUserId) throws GeneralException {
        double totalAmount = 0;
        for (PurchaseRequest purchaseRequest: buyingRequest.getPurchaseRequests()){
            TravelDto travelDto = this.travelControl(purchaseRequest.getTravelId(), loggedUserId);
            totalAmount += travelDto.getAmount();
        }
        return totalAmount;
    }

    public void loggedUserControl(Long loggedUserId) throws GeneralException {
        LoggedUserDto loggedUserDto = this.iAccountServiceClient.loggedUserControl(loggedUserId).getBody();
        if (loggedUserDto == null){
            throw new GeneralException("logged user record not found with id: " + loggedUserId);
        }
        if (loggedUserDto.getUserDto().getUserType() == UserType.ADMIN){
            throw new GeneralException("cannot use this service because user is admin");
        }
    }
}
