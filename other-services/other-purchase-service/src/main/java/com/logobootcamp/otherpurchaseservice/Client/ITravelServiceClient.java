package com.logobootcamp.otherpurchaseservice.Client;

import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.otherpurchaseservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.TravelRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(value = "other-travel-service", url = "http://localhost:8106/api/v1/other/travel")
public interface ITravelServiceClient {

    @GetMapping("/{id}/logged/{loggedUserId}")
    ResponseEntity<TravelDto> getTravel(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException;

    @PatchMapping("/{id}/logged/{loggedUserId}")
    ResponseEntity<TravelDto> updateTravel(@PathVariable Long id, @RequestBody TravelRequest travelRequest, @PathVariable Long loggedUserId) throws GeneralException;
}
