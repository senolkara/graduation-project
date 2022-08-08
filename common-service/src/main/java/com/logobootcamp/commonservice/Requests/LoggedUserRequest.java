package com.logobootcamp.commonservice.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUserRequest {
    private Long loggedUserId;
    private Date date;
}
