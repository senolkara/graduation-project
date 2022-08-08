package com.logobootcamp.admintravelservice.Business.Travel;

import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.commonservice.Requests.LoggedUserRequest;
import com.logobootcamp.commonservice.Requests.TravelRequest;
import com.logobootcamp.commonservice.Requests.TravelSearchRequest;
import com.logobootcamp.admintravelservice.Dao.Travel.ITravelDao;
import com.logobootcamp.admintravelservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelService implements ITravelService {

    /**
     * seyahat(sefer) işlemleri için kullanılan servis
     *
     * bu katmandan data(repository) katmanımıza ulaşıyoruz.
     *
     */
    private final ITravelDao iTravelDao;

    @Override
    public List<TravelDto> getAllTravel(Long loggedUserId) throws GeneralException {
        return this.iTravelDao.getAllTravel(loggedUserId);
    }

    @Override
    public TravelDto getTravel(Long id, Long loggedUserId) throws GeneralException {
        return this.iTravelDao.getTravel(id, loggedUserId);
    }

    @Override
    public TravelDto createTravel(TravelRequest travelRequest, Long loggedUserId) throws GeneralException {
        return this.iTravelDao.createTravel(travelRequest, loggedUserId);
    }

    @Override
    public TravelDto updateTravel(Long id, TravelRequest travelRequest, Long loggedUserId) throws GeneralException {
        return this.iTravelDao.updateTravel(id, travelRequest, loggedUserId);
    }

    @Override
    public void deleteTravel(Long id, Long loggedUserId) throws GeneralException {
        this.iTravelDao.deleteTravel(id, loggedUserId);
    }

    @Override
    public List<TravelDto> searchTravel(TravelSearchRequest travelSearchRequest, Long loggedUserId) throws GeneralException {
        return this.iTravelDao.searchTravel(travelSearchRequest, loggedUserId);
    }
}
