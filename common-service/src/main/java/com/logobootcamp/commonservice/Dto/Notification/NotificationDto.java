package com.logobootcamp.commonservice.Dto.Notification;

import com.logobootcamp.commonservice.Dto.Purchase.PurchaseDto;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {
    private UserDto userDto;
    private List<PurchaseDto> purchaseDtoList;
}
