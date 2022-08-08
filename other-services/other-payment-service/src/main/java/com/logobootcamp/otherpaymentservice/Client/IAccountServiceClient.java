package com.logobootcamp.otherpaymentservice.Client;

import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.otherpaymentservice.ExceptionHandling.GeneralException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "account-service", url = "http://localhost:8101/api/v1/account")
public interface IAccountServiceClient {

    @GetMapping("logged-user-control/{userId}")
    ResponseEntity<LoggedUserDto> loggedUserControl(@PathVariable Long userId) throws GeneralException;
}
