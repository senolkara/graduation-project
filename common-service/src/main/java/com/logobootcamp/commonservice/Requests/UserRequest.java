package com.logobootcamp.commonservice.Requests;

import com.logobootcamp.commonservice.Needs.Enums.GenderType;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private GenderType genderType;
    private String password;
    private UserType userType;
}
