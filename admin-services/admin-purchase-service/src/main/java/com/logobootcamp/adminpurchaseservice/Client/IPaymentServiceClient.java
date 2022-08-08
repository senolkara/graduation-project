package com.logobootcamp.adminpurchaseservice.Client;

import com.logobootcamp.adminpurchaseservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "admin-payment-service", url = "http://localhost:8105/api/v1/admin")
public interface IPaymentServiceClient {

    @GetMapping("/{loggedUserId}/payment/{id}")
    ResponseEntity<PaymentDto> getPayment(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException;

    @DeleteMapping("/{loggedUserId}/payment/{id}")
    void deletePayment(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException;
}
