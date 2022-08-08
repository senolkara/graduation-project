package com.logobootcamp.admintravelservice.Client;

import com.logobootcamp.admintravelservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "admin-user-service", url = "http://localhost:8111/api/v1/admin")
public interface IUserServiceClient {

    @GetMapping("/{loggedUserId}/user/profile/{id}")
    ResponseEntity<UserDto> getUser(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException;
}
