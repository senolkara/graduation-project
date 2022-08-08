package com.logobootcamp.otherpurchaseservice.Client;

import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.commonservice.Requests.BuyingRequest;
import com.logobootcamp.otherpurchaseservice.ExceptionHandling.GeneralException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(value = "other-payment-service", url = "http://localhost:8105/api/v1/other/payment")
public interface IPaymentServiceClient {

    @GetMapping("/{id}/logged/{loggedUserId}")
    ResponseEntity<PaymentDto> getPayment(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException;

    @PostMapping("/logged/{loggedUserId}")
    ResponseEntity<PaymentDto> createPayment(@RequestBody BuyingRequest buyingRequest, @PathVariable Long loggedUserId);
}
