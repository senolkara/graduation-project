package com.logobootcamp.admintravelservice.Business.Travel;

import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.commonservice.Requests.LoggedUserRequest;
import com.logobootcamp.commonservice.Requests.TravelRequest;
import com.logobootcamp.commonservice.Requests.TravelSearchRequest;
import com.logobootcamp.admintravelservice.ExceptionHandling.GeneralException;

import java.util.List;

public interface ITravelService {
    List<TravelDto> getAllTravel(Long loggedUserId) throws GeneralException;
    TravelDto getTravel(Long id, Long loggedUserId) throws GeneralException;
    TravelDto createTravel(TravelRequest travelRequest, Long loggedUserId) throws GeneralException;
    TravelDto updateTravel(Long id, TravelRequest travelRequest, Long loggedUserId) throws GeneralException;
    void deleteTravel(Long id, Long loggedUserId) throws GeneralException;
    List<TravelDto> searchTravel(TravelSearchRequest travelSearchRequest, Long loggedUserId) throws GeneralException;
}
