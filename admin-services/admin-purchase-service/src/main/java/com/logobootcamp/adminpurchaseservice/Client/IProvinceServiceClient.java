package com.logobootcamp.adminpurchaseservice.Client;

import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.adminpurchaseservice.ExceptionHandling.GeneralException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "admin-province-service", url = "http://localhost:8102/api/v1/admin")
public interface IProvinceServiceClient {

    @GetMapping("/{loggedUserId}/province/{id}")
    ResponseEntity<ProvinceDto> getProvince(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException;
}
