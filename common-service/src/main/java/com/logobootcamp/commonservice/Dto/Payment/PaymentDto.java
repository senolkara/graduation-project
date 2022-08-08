package com.logobootcamp.commonservice.Dto.Payment;

import com.logobootcamp.commonservice.Needs.Enums.PaymentStatusType;
import com.logobootcamp.commonservice.Needs.Enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private Long id;
    private String nameOnTheCard;
    private String cardNo;
    private Date validDate;
    private String cvv;
    private Date date;
    private Double totalAmount;
    private PaymentType paymentType;
    private PaymentStatusType paymentStatusType;
}
