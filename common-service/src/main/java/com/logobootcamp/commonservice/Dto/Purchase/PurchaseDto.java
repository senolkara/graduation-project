package com.logobootcamp.commonservice.Dto.Purchase;

import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.commonservice.Dto.Traveller.TravellerDto;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDto {
    private TravelDto travelDto;
    private TravellerDto travellerDto;
    private UserDto userDto;
}
