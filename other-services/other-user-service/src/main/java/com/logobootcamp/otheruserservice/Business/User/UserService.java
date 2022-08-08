package com.logobootcamp.otheruserservice.Business.User;

import com.logobootcamp.commonservice.Requests.UserRequest;
import com.logobootcamp.otheruserservice.Dao.User.IUserDao;
import com.logobootcamp.otheruserservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    /**
     * bireysel ve kurumsal kullancıların kullanıcı işlemleri için kullandığı servis
     *
     * bu katmandan data(repository) katmanımıza ulaşıyoruz.
     *
     */
    private final IUserDao iUserDao;

    @Override
    public UserDto getUser(Long loggedUserId) throws GeneralException {
        return this.iUserDao.getUser(loggedUserId);
    }

    @Override
    public UserDto createUser(UserRequest userRequest) throws NoSuchAlgorithmException, GeneralException {
        return iUserDao.createUser(userRequest);
    }

    @Override
    public UserDto updateUser(UserRequest userRequest, Long loggedUserId) throws GeneralException, NoSuchAlgorithmException {
        return iUserDao.updateUser(userRequest, loggedUserId);
    }

    @Override
    public void deleteUser(Long loggedUserId) throws GeneralException {
        this.iUserDao.deleteUser(loggedUserId);
    }
}
