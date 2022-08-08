package com.logobootcamp.adminpaymentservice.Dao.Payment;

import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.commonservice.Requests.BuyingRequest;
import com.logobootcamp.adminpaymentservice.ExceptionHandling.GeneralException;

public interface IPaymentDao {
    PaymentDto getPayment(Long id, Long loggedUserId) throws GeneralException;
    void deletePayment(Long id, Long loggedUserId) throws GeneralException;
}
