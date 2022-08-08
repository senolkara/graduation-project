package com.logobootcamp.otherpaymentservice.Entities.Payment;

import com.logobootcamp.commonservice.Needs.Enums.PaymentStatusType;
import com.logobootcamp.commonservice.Needs.Enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameOnTheCard;

    private String cardNo;

    private Date validDate;

    private String cvv;

    private Date date;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private PaymentStatusType paymentStatusType;
}
