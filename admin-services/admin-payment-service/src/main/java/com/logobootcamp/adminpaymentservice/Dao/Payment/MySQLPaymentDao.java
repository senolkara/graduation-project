package com.logobootcamp.adminpaymentservice.Dao.Payment;

import com.logobootcamp.adminpaymentservice.Client.IAccountServiceClient;
import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.commonservice.Needs.Enums.PaymentStatusType;
import com.logobootcamp.adminpaymentservice.Entities.Payment.Payment;
import com.logobootcamp.adminpaymentservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Primary
public class MySQLPaymentDao implements IPaymentDao {

    private final IPaymentRepository iPaymentRepository;
    private final IAccountServiceClient iAccountServiceClient;
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
    public void deletePayment(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Payment payment = this.iPaymentRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("payment not found with id: " + id));
        payment.setPaymentStatusType(PaymentStatusType.CANCELED);
        this.iPaymentRepository.save(payment);
    }

    public void loggedUserControl(Long userId) throws GeneralException {
        LoggedUserDto loggedUserDto = this.iAccountServiceClient.loggedUserControl(userId).getBody();
        if (loggedUserDto == null){
            throw new GeneralException("logged user record not found with id: " + userId);
        }
        if (loggedUserDto.getUserDto().getUserType() != UserType.ADMIN){
            throw new GeneralException("cannot use this service because user is not admin");
        }
    }
}
