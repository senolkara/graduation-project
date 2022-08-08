package com.logobootcamp.othertravelservice.Client;

import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.othertravelservice.ExceptionHandling.GeneralException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "other-user-service", url = "http://localhost:8112/api/v1/other/user")
public interface IUserServiceClient {

    @GetMapping("/logged/{loggedUserId}")
    ResponseEntity<UserDto> getUser(@PathVariable Long loggedUserId) throws GeneralException;
}
