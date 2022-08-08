package com.logobootcamp.otherpaymentservice.Business.Payment;

import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.otherpaymentservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.BuyingRequest;
import com.logobootcamp.otherpaymentservice.Dao.Payment.IPaymentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    /**
     * bireysel veya kurumsal kullanıcıların ödeme işlemleri için kullanıldığı servis
     *
     * bu katmandan data(repository) katmanımıza ulaşıyoruz.
     *
     */
    private final IPaymentDao iPaymentDao;

    @Override
    public PaymentDto getPayment(Long id, Long loggedUserId) throws GeneralException {
        return this.iPaymentDao.getPayment(id, loggedUserId);
    }

    @Override
    public PaymentDto createPayment(BuyingRequest buyingRequest, Long loggedUserId) throws GeneralException {
        return this.iPaymentDao.createPayment(buyingRequest, loggedUserId);
    }
}
