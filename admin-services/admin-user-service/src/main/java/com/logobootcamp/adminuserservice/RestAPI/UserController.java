package com.logobootcamp.adminuserservice.RestAPI;

import com.logobootcamp.adminuserservice.Business.User.IUserService;
import com.logobootcamp.adminuserservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.commonservice.Requests.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/{loggedUserId}/user")
@RequiredArgsConstructor
public class UserController {

    /**
     * kullanıcı işlemleri için kullanılan controller.
     *
     * admin kullanıcısı gün içerisinde login olmuş mu olmamış mı kontrolü için
     * api path inden variable olarak loggedUserId yani admin in user id si alınıyor.
     *
     * loggedUserId -> giriş yapmış kullanıcı id si
     *
     */
    private final IUserService iUserService;

    /**
     * kullanıcıları listele
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(@PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iUserService.getAllUser(loggedUserId));
    }

    /**
     * giriş yapmış kullanıcının profili
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile(@PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iUserService.getProfile(loggedUserId));
    }

    /**
     * admin in herhangi bir kullanıcı oluşturması
     *
     * admin e ait bir servis olduğu için her tipten
     * kullanıcı oluşturabilir.
     *
     * @param userRequest
     * @param loggedUserId
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralException
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequest userRequest, @PathVariable Long loggedUserId) throws NoSuchAlgorithmException, GeneralException {
        return ResponseEntity.ok(this.iUserService.createUser(userRequest, loggedUserId));
    }

    /**
     * admin in herhangi bir kullanıcının profilini görmesi
     *
     * admin e ait bir servis olduğu için her kullanıcı
     * detayını görebilir.
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iUserService.getUser(id, loggedUserId));
    }

    /**
     * admin in herhangi bir kullanıcının bilgilerini güncellemesi
     *
     * admin e ait bir servis olduğu için her tipten
     * kullanıcının bilgilerini güncelleyebilir.
     *
     * @param id
     * @param userRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     * @throws NoSuchAlgorithmException
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest, @PathVariable Long loggedUserId) throws GeneralException, NoSuchAlgorithmException {
        return ResponseEntity.ok(this.iUserService.updateUser(id, userRequest, loggedUserId));
    }

    /**
     * admin in herhangi bir kullanıcının durumunu pasif etmesi
     *
     * admin e ait bir servis olduğu için her kullanıcıyı silebilir.
     *
     * @param id
     * @param loggedUserId
     * @throws GeneralException
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        this.iUserService.deleteUser(id, loggedUserId);
    }
}
