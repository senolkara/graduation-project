package com.logobootcamp.commonservice.Dto.Traveller;

import com.logobootcamp.commonservice.Needs.Enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravellerDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private GenderType genderType;
}
