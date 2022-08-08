package com.logobootcamp.commonservice.Dto.LoggedUser;

import com.logobootcamp.commonservice.Dto.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoggedUserDto {
    private UserDto userDto;
    private Date date;
}
