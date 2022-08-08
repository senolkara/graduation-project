package com.logobootcamp.adminuserservice.Business.User;

import com.logobootcamp.adminuserservice.Dao.User.IUserDao;
import com.logobootcamp.adminuserservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.commonservice.Requests.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    /**
     * kullanıcı işlemleri için kullanılan servis
     *
     * bu katmandan data(repository) katmanımıza ulaşıyoruz.
     *
     */
    private final IUserDao iUserDao;

    @Override
    public List<UserDto> getAllUser(Long loggedUserId) throws GeneralException {
        return iUserDao.getAllUser(loggedUserId);
    }

    @Override
    public UserDto getProfile(Long loggedUserId) throws GeneralException {
        return iUserDao.getProfile(loggedUserId);
    }

    @Override
    public UserDto getUser(Long id, Long loggedUserId) throws GeneralException {
        return this.iUserDao.getUser(id, loggedUserId);
    }

    @Override
    public UserDto createUser(UserRequest userRequest, Long loggedUserId) throws NoSuchAlgorithmException, GeneralException {
        return iUserDao.createUser(userRequest, loggedUserId);
    }

    @Override
    public UserDto updateUser(Long id, UserRequest userRequest, Long loggedUserId) throws GeneralException, NoSuchAlgorithmException {
        return iUserDao.updateUser(id, userRequest, loggedUserId);
    }

    @Override
    public void deleteUser(Long id, Long loggedUserId) throws GeneralException {
        this.iUserDao.deleteUser(id, loggedUserId);
    }
}
