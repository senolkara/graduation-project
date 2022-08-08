package com.logobootcamp.adminuserservice.Business.User;

import com.logobootcamp.adminuserservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.commonservice.Requests.UserRequest;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface IUserService {
    List<UserDto> getAllUser(Long loggedUserId) throws GeneralException;
    UserDto getProfile(Long loggedUserId) throws GeneralException;
    UserDto getUser(Long id, Long loggedUserId) throws GeneralException;
    UserDto createUser(UserRequest userRequest, Long loggedUserId) throws NoSuchAlgorithmException, GeneralException;
    UserDto updateUser(Long id, UserRequest userRequest, Long loggedUserId) throws GeneralException, NoSuchAlgorithmException;
    void deleteUser(Long id, Long loggedUserId) throws GeneralException;
}
