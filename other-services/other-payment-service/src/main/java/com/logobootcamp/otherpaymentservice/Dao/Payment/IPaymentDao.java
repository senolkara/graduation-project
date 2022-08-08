package com.logobootcamp.otherpaymentservice.Dao.Payment;

import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.otherpaymentservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.BuyingRequest;

public interface IPaymentDao {
    PaymentDto getPayment(Long id, Long loggedUserId) throws GeneralException;
    PaymentDto createPayment(BuyingRequest buyingRequest, Long loggedUserId) throws GeneralException;
}
