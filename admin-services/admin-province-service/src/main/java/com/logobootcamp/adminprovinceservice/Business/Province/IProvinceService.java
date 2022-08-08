package com.logobootcamp.adminprovinceservice.Business.Province;

import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.adminprovinceservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.LoggedUserRequest;
import com.logobootcamp.commonservice.Requests.ProvinceRequest;

import java.util.List;

public interface IProvinceService {
    List<ProvinceDto> getAllProvince(Long loggedUserId) throws GeneralException;
    ProvinceDto getProvince(Long id, Long loggedUserId) throws GeneralException;
    ProvinceDto createProvince(ProvinceRequest provinceRequest, Long loggedUserId) throws GeneralException;
    ProvinceDto updateProvince(Long id, ProvinceRequest provinceRequest, Long loggedUserId) throws GeneralException;
    void deleteProvince(Long id, Long loggedUserId) throws GeneralException;
}
