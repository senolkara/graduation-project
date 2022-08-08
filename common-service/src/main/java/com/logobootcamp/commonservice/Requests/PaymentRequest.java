package com.logobootcamp.commonservice.Requests;

import com.logobootcamp.commonservice.Needs.Enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Long id;
    private String nameOnTheCard;
    private String cardNo;
    private Date validDate;
    private String cvv;
    private PaymentType paymentType;
    private Boolean cardInfoSaveOrNot;
}
