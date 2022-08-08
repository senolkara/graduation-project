package com.logobootcamp.adminprovinceservice.Business.Province;

import com.logobootcamp.commonservice.Requests.ProvinceRequest;
import com.logobootcamp.adminprovinceservice.Dao.Province.IProvinceDao;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.adminprovinceservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceService implements IProvinceService {

    /**
     * şehir işlemleri için kullanılan servis
     *
     * bu katmandan data(repository) katmanımıza ulaşıyoruz.
     *
     */
    private final IProvinceDao iProvinceDao;

    @Override
    public List<ProvinceDto> getAllProvince(Long loggedUserId) throws GeneralException {
        return this.iProvinceDao.getAllProvince(loggedUserId);
    }

    @Override
    public ProvinceDto getProvince(Long id, Long loggedUserId) throws GeneralException {
        return this.iProvinceDao.getProvince(id, loggedUserId);
    }

    @Override
    public ProvinceDto createProvince(ProvinceRequest provinceRequest, Long loggedUserId) throws GeneralException {
        return this.iProvinceDao.createProvince(provinceRequest, loggedUserId);
    }

    @Override
    public ProvinceDto updateProvince(Long id, ProvinceRequest provinceRequest, Long loggedUserId) throws GeneralException {
        return this.iProvinceDao.updateProvince(id, provinceRequest, loggedUserId);
    }

    @Override
    public void deleteProvince(Long id, Long loggedUserId) throws GeneralException {
        this.iProvinceDao.deleteProvince(id, loggedUserId);
    }
}
