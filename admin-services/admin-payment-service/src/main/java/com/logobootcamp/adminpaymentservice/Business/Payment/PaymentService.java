package com.logobootcamp.adminpaymentservice.Business.Payment;

import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.commonservice.Requests.BuyingRequest;
import com.logobootcamp.adminpaymentservice.Dao.Payment.IPaymentDao;
import com.logobootcamp.adminpaymentservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    /**
     * ödeme işlemleri için kullanılan servis
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
    public void deletePayment(Long id, Long loggedUserId) throws GeneralException {
        this.iPaymentDao.deletePayment(id, loggedUserId);
    }
}
