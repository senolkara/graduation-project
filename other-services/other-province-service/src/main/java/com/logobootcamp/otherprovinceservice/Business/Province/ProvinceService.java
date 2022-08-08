package com.logobootcamp.otherprovinceservice.Business.Province;

import com.logobootcamp.otherprovinceservice.Dao.Province.IProvinceDao;
import com.logobootcamp.otherprovinceservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvinceService implements IProvinceService {

    /**
     * bireysel veya kurumsal kullancıların şehir işlemleri için kullanıldığı servis
     *
     * bu katmandan data(repository) katmanımıza ulaşıyoruz.
     *
     */
    private final IProvinceDao iProvinceDao;

    @Override
    public ProvinceDto getProvince(Long id, Long loggedUserId) throws GeneralException {
        return this.iProvinceDao.getProvince(id, loggedUserId);
    }
}
