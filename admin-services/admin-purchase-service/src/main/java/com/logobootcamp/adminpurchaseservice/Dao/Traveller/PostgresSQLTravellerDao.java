package com.logobootcamp.adminpurchaseservice.Dao.Traveller;

import com.logobootcamp.commonservice.Dto.Traveller.TravellerDto;
import com.logobootcamp.commonservice.Requests.TravellerRequest;
import com.logobootcamp.adminpurchaseservice.Entities.Traveller.Traveller;
import com.logobootcamp.adminpurchaseservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostgresSQLTravellerDao implements ITravellerDao {

    private final ITravellerRepository iTravellerRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<TravellerDto> getAllTraveller() {
        List<Traveller> travellerList = this.iTravellerRepository.findAll();
        List<TravellerDto> travellerDtoList = travellerList.stream().map(traveller -> modelMapper.map(traveller, TravellerDto.class)).collect(Collectors.toList());
        return travellerDtoList;
    }

    @Override
    public TravellerDto getTraveller(Long id) throws GeneralException {
        Traveller traveller = this.iTravellerRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("traveller not found with id: " + id));
        return modelMapper.map(traveller, TravellerDto.class);
    }

    @Override
    public TravellerDto createTraveller(TravellerRequest travellerRequest) {
        Traveller traveller = modelMapper.map(travellerRequest, Traveller.class);
        return modelMapper.map(this.iTravellerRepository.save(traveller), TravellerDto.class);
    }

    @Override
    public TravellerDto updateTraveller(Long id, TravellerRequest travellerRequest) throws GeneralException {
        Traveller traveller = this.iTravellerRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("traveller not found with id: " + id));
        traveller.setFirstName(traveller.getFirstName());
        traveller.setLastName(travellerRequest.getLastName());
        traveller.setEmail(travellerRequest.getEmail());
        traveller.setPhone(travellerRequest.getPhone());
        traveller.setGenderType(travellerRequest.getGenderType());
        return modelMapper.map(iTravellerRepository.save(traveller), TravellerDto.class);
    }

    @Override
    public void deleteTraveller(Long id) throws GeneralException {
        Traveller traveller = this.iTravellerRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("traveller not found with id: " + id));
        this.iTravellerRepository.deleteById(traveller.getId());
    }
}
