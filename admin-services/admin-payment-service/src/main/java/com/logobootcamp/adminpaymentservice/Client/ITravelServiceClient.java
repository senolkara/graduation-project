package com.logobootcamp.adminpaymentservice.Client;

import com.logobootcamp.adminpaymentservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "admin-travel-service", url = "http://localhost:8106/api/v1/admin")
public interface ITravelServiceClient {

    @GetMapping("/{loggedUserId}/travel/{id}")
    ResponseEntity<TravelDto> getTravel(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException;
}
