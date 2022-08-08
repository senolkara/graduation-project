package com.logobootcamp.otheruserservice.Dao.User;

import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Requests.UserRequest;
import com.logobootcamp.otheruserservice.Client.IAccountServiceClient;
import com.logobootcamp.otheruserservice.Entities.User.User;
import com.logobootcamp.otheruserservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.commonservice.Needs.EncryptPassword;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;

@Repository
@RequiredArgsConstructor
public class MySQLUserDao implements IUserDao {

    private final IUserRepository iUserRepository;
    private final IAccountServiceClient iAccountServiceClient;
    private final ModelMapper modelMapper;

    @Override
    public UserDto getUser(Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        User user = this.iUserRepository
                .findById(loggedUserId)
                .orElseThrow(() -> new GeneralException("user not found with id: " + loggedUserId));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto createUser(UserRequest userRequest) throws NoSuchAlgorithmException, GeneralException {
        if (userRequest.getUserType() == UserType.ADMIN){
            throw new GeneralException("admin user cannot be added, personal or corporate user can be added");
        }
        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(EncryptPassword.encryptString(userRequest.getPassword()));
        user.setStatusType(StatusType.ACTIVE);
        return modelMapper.map(iUserRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto updateUser(UserRequest userRequest, Long loggedUserId) throws GeneralException, NoSuchAlgorithmException {
        this.loggedUserControl(loggedUserId);
        if (userRequest.getUserType() == UserType.ADMIN){
            throw new GeneralException("admin user cannot be added, personal or corporate user can be added");
        }
        User user = this.iUserRepository
                .findById(loggedUserId)
                .orElseThrow(() -> new GeneralException("user not found with id: " + loggedUserId));
        user.setFirstName(user.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setGenderType(userRequest.getGenderType());
        user.setPassword(EncryptPassword.encryptString(userRequest.getPassword()));
        user.setUserType(userRequest.getUserType());
        return modelMapper.map(iUserRepository.save(user), UserDto.class);
    }

    @Override
    public void deleteUser(Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        User user = this.iUserRepository
                .findById(loggedUserId)
                .orElseThrow(() -> new GeneralException("user not found with id: " + loggedUserId));
        user.setStatusType(StatusType.PASSIVE);
        this.iUserRepository.save(user);
    }

    public void loggedUserControl(Long loggedUserId) throws GeneralException {
        LoggedUserDto loggedUserDto = this.iAccountServiceClient.loggedUserControl(loggedUserId).getBody();
        if (loggedUserDto == null){
            throw new GeneralException("logged user record not found with id: " + loggedUserId);
        }
        if (loggedUserDto.getUserDto().getUserType() == UserType.ADMIN){
            throw new GeneralException("cannot use this service because user is admin");
        }
    }
}
