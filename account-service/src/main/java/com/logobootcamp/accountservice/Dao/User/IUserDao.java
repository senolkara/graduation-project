package com.logobootcamp.accountservice.Dao.User;

import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.accountservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.LoginRequest;

import java.security.NoSuchAlgorithmException;

public interface IUserDao {
    LoggedUserDto login(LoginRequest loginRequest) throws GeneralException, NoSuchAlgorithmException;
    LoggedUserDto loggedUserControl(Long userId) throws GeneralException;
}
