package com.logobootcamp.commonservice.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyingRequest {
    private PurchaseRequest[] purchaseRequests;
    private PaymentRequest paymentRequest;
}
