package com.logobootcamp.accountservice.Components;

import com.logobootcamp.accountservice.Dao.User.IUserRepository;
import com.logobootcamp.accountservice.Entities.User.User;
import com.logobootcamp.commonservice.Needs.EncryptPassword;
import com.logobootcamp.commonservice.Needs.Enums.GenderType;
import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleDataRunner implements CommandLineRunner {

    private final IUserRepository iUserRepository;

    @Override
    public void run(String... args) throws Exception {
        User topUser = this.iUserRepository.findTopByOrderByIdDesc();
        if (topUser == null){
            List<User> userList = new ArrayList<>();
            userList.add(new User(1L,
                    "senol",
                    "karakurt",
                    "abc@hotmail.com",
                    "05056897788",
                    EncryptPassword.encryptString("12345678"),
                    GenderType.MALE,
                    UserType.ADMIN,
                    StatusType.ACTIVE));
            userList.add(new User(2L,
                    "senol",
                    "karakurt",
                    "abcd@hotmail.com",
                    "05056897788",
                    EncryptPassword.encryptString("12345678"),
                    GenderType.MALE,
                    UserType.PERSONAL,
                    StatusType.ACTIVE));
            userList.add(new User(3L,
                    "senol",
                    "karakurt",
                    "abcde@hotmail.com",
                    "05056897788",
                    EncryptPassword.encryptString("12345678"),
                    GenderType.MALE,
                    UserType.CORPORATE,
                    StatusType.ACTIVE));
            this.iUserRepository.saveAll(userList);
        }
    }
}
