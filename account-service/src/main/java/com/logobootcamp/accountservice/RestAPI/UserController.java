package com.logobootcamp.accountservice.RestAPI;

import com.logobootcamp.accountservice.Business.User.IUserService;
import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.accountservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class UserController {

    /**
     * account işlemleri için kullanılan controller.
     */
    private final IUserService iUserService;

    /**
     * herhangi bir kullanıcı login olabilmek için kullanır.
     * diğer kullanıcılar için örnek datalar SampleDataRunner
     * component'i ile veritabanına kaydedilmiştir.
     *
     * örnek admin girişi:
     *
         {
             "email": "abc@hotmail.com",
             "password": "12345678"
         }
     *
     * @param loginRequest
     * @return
     * @throws GeneralException
     * @throws NoSuchAlgorithmException
     */
    @PostMapping("/login")
    public ResponseEntity<LoggedUserDto> login(@RequestBody LoginRequest loginRequest) throws GeneralException, NoSuchAlgorithmException {
        return ResponseEntity.ok(this.iUserService.login(loginRequest));
    }

    /**
     * bir kullanıcının gün içerisinde login olup olmadığının kontrolü için kullanılır.
     *
     * @param userId
     * @return
     * @throws GeneralException
     */
    @GetMapping("logged-user-control/{userId}")
    public ResponseEntity<LoggedUserDto> loggedUserControl(@PathVariable Long userId) throws GeneralException {
        return ResponseEntity.ok(this.iUserService.loggedUserControl(userId));
    }
}
