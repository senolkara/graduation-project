package com.logobootcamp.admintravelservice.Dao.Travel;

import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.VehicleType;
import com.logobootcamp.admintravelservice.Entities.Travel.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ITravelRepository extends JpaRepository<Travel, Long> {
    List<Travel> findAllByProvinceFromAndProvinceWhereAndStatusTypeOrDateOrVehicleType(Long provinceFrom, Long provinceWhere, StatusType statusType, Date date, VehicleType vehicleType);
}
