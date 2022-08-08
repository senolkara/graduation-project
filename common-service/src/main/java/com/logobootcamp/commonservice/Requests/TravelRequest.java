package com.logobootcamp.commonservice.Requests;

import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelRequest {
    private Long id;
    private Long provinceFrom;
    private Long provinceWhere;
    private Double amount;
    private Date date;
    private Integer capacity;
    private VehicleType vehicleType;
    private StatusType statusType;
}
