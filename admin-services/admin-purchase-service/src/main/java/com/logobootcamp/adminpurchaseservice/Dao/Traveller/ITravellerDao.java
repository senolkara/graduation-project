package com.logobootcamp.adminpurchaseservice.Dao.Traveller;

import com.logobootcamp.commonservice.Dto.Traveller.TravellerDto;
import com.logobootcamp.commonservice.Requests.TravellerRequest;
import com.logobootcamp.adminpurchaseservice.ExceptionHandling.GeneralException;

import java.util.List;

public interface ITravellerDao {
    List<TravellerDto> getAllTraveller();
    TravellerDto getTraveller(Long id) throws GeneralException;
    TravellerDto createTraveller(TravellerRequest travellerRequest);
    TravellerDto updateTraveller(Long id, TravellerRequest travellerRequest) throws GeneralException;
    void deleteTraveller(Long id) throws GeneralException;
}
