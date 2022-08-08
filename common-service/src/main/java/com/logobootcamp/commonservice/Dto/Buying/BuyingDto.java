package com.logobootcamp.commonservice.Dto.Buying;

import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.commonservice.Dto.Purchase.PurchaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyingDto {
    private List<PurchaseDto> purchaseDtoList;
    private PaymentDto paymentDto;
}
