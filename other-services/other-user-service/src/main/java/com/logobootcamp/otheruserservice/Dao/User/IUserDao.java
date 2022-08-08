package com.logobootcamp.otheruserservice.Dao.User;

import com.logobootcamp.commonservice.Requests.UserRequest;
import com.logobootcamp.otheruserservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.User.UserDto;

import java.security.NoSuchAlgorithmException;

public interface IUserDao {
    UserDto getUser(Long loggedUserId) throws GeneralException;
    UserDto createUser(UserRequest userRequest) throws NoSuchAlgorithmException, GeneralException;
    UserDto updateUser(UserRequest userRequest, Long loggedUserId) throws GeneralException, NoSuchAlgorithmException;
    void deleteUser(Long loggedUserId) throws GeneralException;
}
