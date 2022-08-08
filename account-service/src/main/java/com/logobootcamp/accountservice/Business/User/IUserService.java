package com.logobootcamp.accountservice.Business.User;

import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.accountservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.LoginRequest;

import java.security.NoSuchAlgorithmException;

public interface IUserService {
    LoggedUserDto login(LoginRequest loginRequest) throws GeneralException, NoSuchAlgorithmException;
    LoggedUserDto loggedUserControl(Long userId) throws GeneralException;
}
