package com.logobootcamp.commonservice.Dto.User;

import com.logobootcamp.commonservice.Needs.Enums.GenderType;
import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private GenderType genderType;
    //private String password;
    private UserType userType;
    private StatusType statusType;
}
