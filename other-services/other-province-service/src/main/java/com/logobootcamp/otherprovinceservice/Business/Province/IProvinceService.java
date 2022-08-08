package com.logobootcamp.otherprovinceservice.Business.Province;

import com.logobootcamp.otherprovinceservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.commonservice.Requests.ProvinceRequest;

import java.util.List;

public interface IProvinceService {
    ProvinceDto getProvince(Long id, Long loggedUserId) throws GeneralException;
}
