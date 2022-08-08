package com.logobootcamp.accountservice.Dao.User;

import com.logobootcamp.accountservice.Dao.LoggedUser.ILoggedUserRepository;
import com.logobootcamp.accountservice.Entities.LoggedUser.LoggedUser;
import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.accountservice.Entities.User.User;
import com.logobootcamp.accountservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Needs.EncryptPassword;
import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Requests.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;

@Repository
@RequiredArgsConstructor
public class MySQLUserDao implements IUserDao {

    private final IUserRepository iUserRepository;
    private final ILoggedUserRepository iLoggedUserRepository;
    private final ModelMapper modelMapper;

    @Override
    public LoggedUserDto login(LoginRequest loginRequest) throws GeneralException, NoSuchAlgorithmException {
        User user = this.iUserRepository.findUserByEmail(loginRequest.getEmail());
        if (user == null){
            throw new GeneralException("user not found with email: " + loginRequest.getEmail());
        }
        if (user.getStatusType() == StatusType.PASSIVE){
            throw new GeneralException("user is passive with email: " + user.getEmail());
        }
        String hashingPass = EncryptPassword.encryptString(loginRequest.getPassword());
        String hashedPass = user.getPassword();
        if (!hashingPass.equals(hashedPass)){
            throw new GeneralException("user login failed, please check your information: " + loginRequest.getEmail());
        }
        Date date = new Date(System.currentTimeMillis());
        LoggedUser loggedUser = new LoggedUser();
        loggedUser.setUser(user);
        loggedUser.setDate(date);
        Long countAllByUserAndDate = this.iLoggedUserRepository.countAllByUserAndDate(user, date);
        if (countAllByUserAndDate <= 0){
            this.iLoggedUserRepository.save(loggedUser);
        }
        LoggedUserDto loggedUserDto = LoggedUserDto.builder()
                .userDto(modelMapper.map(user, UserDto.class))
                .date(date)
                .build();
        return loggedUserDto;
    }

    @Override
    public LoggedUserDto loggedUserControl(Long userId) throws GeneralException {
        User user = this.iUserRepository
                .findById(userId)
                .orElseThrow(() -> new GeneralException("user not found with id: " + userId));
        Date date = new Date(System.currentTimeMillis());
        Long countAllByUserAndDate = this.iLoggedUserRepository.countAllByUserAndDate(user, date);
        if (countAllByUserAndDate <= 0){
            throw new GeneralException("user is not logged in today: " + user.getEmail());
        }
        LoggedUserDto loggedUserDto = LoggedUserDto.builder()
                .userDto(modelMapper.map(user, UserDto.class))
                .date(date)
                .build();
        return loggedUserDto;
    }
}
