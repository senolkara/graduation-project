package com.logobootcamp.adminpaymentservice.Dao.Payment;

import com.logobootcamp.adminpaymentservice.Client.IAccountServiceClient;
import com.logobootcamp.adminpaymentservice.Client.ITravelServiceClient;
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
public class PostgresSQLPaymentDao implements IPaymentDao {

    private final IPaymentRepository iPaymentRepository;
    private final IAccountServiceClient iAccountServiceClient;
    private final ITravelServiceClient iTravelServiceClient;
    private final ModelMapper modelMapper;

    /**
     * ödeme detayı
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public PaymentDto getPayment(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Payment payment = this.iPaymentRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("payment not found with id: " + id));
        return modelMapper.map(payment, PaymentDto.class);
    }

    /**
     * ödeme iptali
     *
     * @param id
     * @param loggedUserId
     * @throws GeneralException
     */
    @Override
    public void deletePayment(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Payment payment = this.iPaymentRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("payment not found with id: " + id));

        payment.setPaymentStatusType(PaymentStatusType.CANCELED);
        this.iPaymentRepository.save(payment);
    }

    /**
     * kullanıcının gün içerisinde giriş yapıp yaptığının kontrolü
     *
     * @param userId
     * @throws GeneralException
     */
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
