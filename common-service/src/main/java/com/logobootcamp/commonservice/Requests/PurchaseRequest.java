package com.logobootcamp.commonservice.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
    private Long id;
    private Long travelId;
    private TravellerRequest travellerRequest;
}
