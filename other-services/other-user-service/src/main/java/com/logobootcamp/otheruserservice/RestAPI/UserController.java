package com.logobootcamp.otheruserservice.RestAPI;

import com.logobootcamp.commonservice.Requests.UserRequest;
import com.logobootcamp.otheruserservice.Business.User.IUserService;
import com.logobootcamp.otheruserservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/other/user")
@RequiredArgsConstructor
public class UserController {

    /**
     * bireysel veya kurumsal kullanıcıların kullanıcı işlemleri için kullandığı controller.
     *
     * admin kullanıcısı gün içerisinde login olmuş mu olmamış mı kontrolü için
     * api path inden variable olarak loggedUserId yani admin in user id si alınıyor.
     *
     * loggedUserId -> giriş yapmış kullanıcı id si
     *
     */
    private final IUserService iUserService;

    /**
     * sign up işlemi yani kullanıcı-hesap oluşturması
     *
     * @param userRequest
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralException
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequest userRequest) throws NoSuchAlgorithmException, GeneralException {
        return ResponseEntity.ok(this.iUserService.createUser(userRequest));
    }

    /**
     * profilini görmesi
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/logged/{loggedUserId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iUserService.getUser(loggedUserId));
    }

    /**
     * bilgilerini güncellemesi
     *
     * @param userRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     * @throws NoSuchAlgorithmException
     */
    @PatchMapping("/logged/{loggedUserId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long loggedUserId) throws GeneralException, NoSuchAlgorithmException {
        return ResponseEntity.ok(this.iUserService.updateUser(userRequest, loggedUserId));
    }

    /**
     * hesabın pasife getirilmesi
     *
     * @param loggedUserId
     * @throws GeneralException
     */
    @DeleteMapping("/logged/{loggedUserId}")
    public void deleteUser(@PathVariable Long loggedUserId) throws GeneralException {
        this.iUserService.deleteUser(loggedUserId);
    }
}
