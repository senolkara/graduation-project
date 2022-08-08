package com.logobootcamp.adminpaymentservice.Business.Payment;

import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.adminpaymentservice.ExceptionHandling.GeneralException;

public interface IPaymentService {
    PaymentDto getPayment(Long id, Long loggedUserId) throws GeneralException;
    void deletePayment(Long id, Long loggedUserId) throws GeneralException;
}
