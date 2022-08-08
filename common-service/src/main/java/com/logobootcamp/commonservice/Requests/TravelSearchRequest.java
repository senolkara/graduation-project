package com.logobootcamp.commonservice.Requests;

import com.logobootcamp.commonservice.Needs.Enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelSearchRequest {
    private Long provinceFrom;
    private Long provinceWhere;
    private Date date;
    private VehicleType vehicleType;
}
