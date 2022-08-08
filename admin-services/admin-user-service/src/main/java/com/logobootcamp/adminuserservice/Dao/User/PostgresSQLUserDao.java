package com.logobootcamp.adminuserservice.Dao.User;

import com.logobootcamp.adminuserservice.Client.IAccountServiceClient;
import com.logobootcamp.adminuserservice.Entities.User.User;
import com.logobootcamp.adminuserservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.commonservice.Needs.EncryptPassword;
import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import com.logobootcamp.commonservice.Requests.UserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Primary
public class PostgresSQLUserDao implements IUserDao {

    private final IUserRepository iUserRepository;
    private final IAccountServiceClient iAccountServiceClient;
    private final ModelMapper modelMapper;

    /**
     * kullanıcıları listele
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public List<UserDto> getAllUser(Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        List<User> users = this.iUserRepository.findAll();
        List<UserDto> userDtoList = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtoList;
    }

    /**
     * giriş yapmış kullanıcının profili
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public UserDto getProfile(Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        User user = this.iUserRepository
                .findById(loggedUserId)
                .orElseThrow(() -> new GeneralException("user not found with id: " + loggedUserId));
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * admin in herhangi bir kullanıcının profilini görmesi
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public UserDto getUser(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        User user = this.iUserRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("user not found with id: " + id));
        return modelMapper.map(user, UserDto.class);
    }

    /**
     * admin in herhangi bir kullanıcı oluşturması
     *
     * @param userRequest
     * @param loggedUserId
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralException
     */
    @Override
    public UserDto createUser(UserRequest userRequest, Long loggedUserId) throws NoSuchAlgorithmException, GeneralException {
        this.loggedUserControl(loggedUserId);
        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(EncryptPassword.encryptString(userRequest.getPassword()));
        user.setStatusType(StatusType.ACTIVE);
        return modelMapper.map(iUserRepository.save(user), UserDto.class);
    }

    /**
     * admin in herhangi bir kullanıcının bilgilerini güncellemesi
     *
     * @param id
     * @param userRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     * @throws NoSuchAlgorithmException
     */
    @Override
    public UserDto updateUser(Long id, UserRequest userRequest, Long loggedUserId) throws GeneralException, NoSuchAlgorithmException {
        this.loggedUserControl(loggedUserId);
        User user = this.iUserRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("user not found with id: " + id));
        user.setFirstName(user.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setGenderType(userRequest.getGenderType());
        user.setPassword(EncryptPassword.encryptString(userRequest.getPassword()));
        user.setUserType(userRequest.getUserType());
        return modelMapper.map(iUserRepository.save(user), UserDto.class);
    }

    /**
     * admin in herhangi bir kullanıcının durumunu pasif etmesi
     *
     * @param id
     * @param loggedUserId
     * @throws GeneralException
     */
    @Override
    public void deleteUser(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        User user = this.iUserRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("user not found with id: " + id));
        user.setStatusType(StatusType.PASSIVE);
        this.iUserRepository.save(user);
    }

    /**
     * kullanıcının gün içerisinde giriş yapıp yaptığının kontrolü
     *
     * @param loggedUserId
     * @throws GeneralException
     */
    public void loggedUserControl(Long loggedUserId) throws GeneralException {
        LoggedUserDto loggedUserDto = this.iAccountServiceClient.loggedUserControl(loggedUserId).getBody();
        if (loggedUserDto == null){
            throw new GeneralException("logged user record not found with id: " + loggedUserId);
        }
        if (loggedUserDto.getUserDto().getUserType() != UserType.ADMIN){
            throw new GeneralException("cannot use this service because user is not admin");
        }
    }
}
