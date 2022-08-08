package com.logobootcamp.admintravelservice.Client;

import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.admintravelservice.ExceptionHandling.GeneralException;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "admin-province-service", url = "http://localhost:8102/api/v1/admin")
public interface IProvinceServiceClient {

    @GetMapping("/{loggedUserId}/province/{id}")
    ResponseEntity<ProvinceDto> getProvince(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException;
}
