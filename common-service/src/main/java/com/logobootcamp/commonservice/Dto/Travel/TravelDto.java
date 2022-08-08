package com.logobootcamp.commonservice.Dto.Travel;

import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelDto {
    private ProvinceDto provinceFrom;
    private ProvinceDto provinceWhere;
    private Double amount;
    private Date date;
    private Integer capacity;
    private VehicleType vehicleType;
    private StatusType statusType;
}
