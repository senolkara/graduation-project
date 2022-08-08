package com.logobootcamp.commonservice.Requests;

import com.logobootcamp.commonservice.Needs.Enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravellerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private GenderType genderType;
}
