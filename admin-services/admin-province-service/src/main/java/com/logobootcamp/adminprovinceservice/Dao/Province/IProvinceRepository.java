package com.logobootcamp.adminprovinceservice.Dao.Province;

import com.logobootcamp.adminprovinceservice.Entities.Province.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProvinceRepository extends JpaRepository<Province, Long> {
    Province findTopByOrderByIdDesc();
}
