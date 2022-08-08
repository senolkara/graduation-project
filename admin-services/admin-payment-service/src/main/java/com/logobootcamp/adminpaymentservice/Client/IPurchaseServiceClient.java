package com.logobootcamp.adminpaymentservice.Client;

import com.logobootcamp.adminpaymentservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.Buying.BuyingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "admin-purchase-service", url = "http://localhost:8109/api/v1/admin")
public interface IPurchaseServiceClient {

    @GetMapping("/{loggedUserId}/purchase/{id}")
    ResponseEntity<BuyingDto> getPurchase(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException;
}
