package com.logobootcamp.otherprovinceservice.Dao.Province;

import com.logobootcamp.otherprovinceservice.Entities.Province.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProvinceRepository extends JpaRepository<Province, Long> {
    Province findTopByOrderByIdDesc();
}
