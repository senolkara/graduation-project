package com.logobootcamp.othertravelservice.Client;

import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.othertravelservice.ExceptionHandling.GeneralException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "admin-province-service", url = "http://localhost:8110/api/v1/other/province")
public interface IProvinceServiceClient {

    @GetMapping("/{id}/logged/{loggedUserId}")
    ResponseEntity<ProvinceDto> getProvince(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException;
}
