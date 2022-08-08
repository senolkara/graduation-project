package com.logobootcamp.accountservice.Business.User;

import com.logobootcamp.accountservice.Dao.User.IUserDao;
import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.accountservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserDao iUserDao;

    @Override
    public LoggedUserDto login(LoginRequest loginRequest) throws GeneralException, NoSuchAlgorithmException {
        return this.iUserDao.login(loginRequest);
    }

    @Override
    public LoggedUserDto loggedUserControl(Long userId) throws GeneralException {
        return this.iUserDao.loggedUserControl(userId);
    }
}
