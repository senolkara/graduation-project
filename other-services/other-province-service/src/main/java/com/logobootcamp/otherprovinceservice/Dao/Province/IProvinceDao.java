package com.logobootcamp.otherprovinceservice.Dao.Province;

import com.logobootcamp.otherprovinceservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;

public interface IProvinceDao {
    ProvinceDto getProvince(Long id, Long loggedUserId) throws GeneralException;
}
