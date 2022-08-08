package com.logobootcamp.othertravelservice.Dao.Travel;

import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import com.logobootcamp.commonservice.Requests.LoggedUserRequest;
import com.logobootcamp.commonservice.Requests.TravelRequest;
import com.logobootcamp.commonservice.Requests.TravelSearchRequest;
import com.logobootcamp.othertravelservice.Client.IAccountServiceClient;
import com.logobootcamp.othertravelservice.Client.IProvinceServiceClient;
import com.logobootcamp.othertravelservice.Entities.Travel.Travel;
import com.logobootcamp.othertravelservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostgresSQLTravelDao implements ITravelDao {

    private final ITravelRepository iTravelRepository;
    private final IAccountServiceClient iAccountServiceClient;
    private final IProvinceServiceClient iProvinceServiceClient;

    /**
     * bireysel veya kurumsal kullanıcıların bütün seyahatleri(seferleri) listelemesi
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public List<TravelDto> getAllTravel(Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        List<TravelDto> travelDtoList = new ArrayList<>();
        List<Travel> travelList = this.iTravelRepository.findAll();
        for (Travel travel:travelList){
            TravelDto travelDto = TravelDto.builder()
                    .provinceFrom(this.provinceControl(travel.getProvinceFrom(), loggedUserId))
                    .provinceWhere(this.provinceControl(travel.getProvinceWhere(), loggedUserId))
                    .amount(travel.getAmount())
                    .date(travel.getDate())
                    .capacity(travel.getCapacity())
                    .vehicleType(travel.getVehicleType())
                    .statusType(travel.getStatusType())
                    .build();
            travelDtoList.add(travelDto);
        }
        return travelDtoList;
    }

    /**
     * bireysel veya kurumsal kullanıcıların seyahat(sefer) detayını incelemesi
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public TravelDto getTravel(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Travel travel = this.iTravelRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("travel not found with id: " + id));
        TravelDto travelDto = TravelDto.builder()
                .provinceFrom(this.provinceControl(travel.getProvinceFrom(), loggedUserId))
                .provinceWhere(this.provinceControl(travel.getProvinceWhere(), loggedUserId))
                .amount(travel.getAmount())
                .date(travel.getDate())
                .capacity(travel.getCapacity())
                .vehicleType(travel.getVehicleType())
                .statusType(travel.getStatusType())
                .build();
        return travelDto;
    }

    /**
     * bireysel veya kurumsal kullanıcıların seyahatini(seferini) satınaldıktan sonra seyahat(sefer) güncelleme işlemi
     *
     * @param id
     * @param travelRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public TravelDto updateTravel(Long id, TravelRequest travelRequest, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        this.travelOtherControl(travelRequest);
        Travel travel = this.iTravelRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("travel not found with id: " + id));
        travel.setCapacity(travelRequest.getCapacity());
        this.iTravelRepository.save(travel);
        TravelDto travelDto = TravelDto.builder()
                .provinceFrom(this.provinceControl(travelRequest.getProvinceFrom(), loggedUserId))
                .provinceWhere(this.provinceControl(travelRequest.getProvinceWhere(), loggedUserId))
                .amount(travelRequest.getAmount())
                .date(travelRequest.getDate())
                .capacity(travelRequest.getCapacity())
                .vehicleType(travelRequest.getVehicleType())
                .statusType(travelRequest.getStatusType())
                .build();
        return travelDto;
    }

    /**
     * şehir bilgisi, tarih, taşıt türüne göre arama
     *
     * @param travelSearchRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public List<TravelDto> searchTravel(TravelSearchRequest travelSearchRequest, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        List<Travel> travelList = this.iTravelRepository
                .findAllByProvinceFromAndProvinceWhereAndStatusTypeOrDateOrVehicleType(
                        travelSearchRequest.getProvinceFrom(),
                        travelSearchRequest.getProvinceWhere(),
                        StatusType.ACTIVE,
                        travelSearchRequest.getDate(),
                        travelSearchRequest.getVehicleType()
                );
        List<TravelDto> travelDtoList = new ArrayList<>();
        for (Travel travel:travelList){
            TravelDto travelDto = TravelDto.builder()
                    .provinceFrom(this.provinceControl(travel.getProvinceFrom(), loggedUserId))
                    .provinceWhere(this.provinceControl(travel.getProvinceWhere(), loggedUserId))
                    .amount(travel.getAmount())
                    .date(travel.getDate())
                    .capacity(travel.getCapacity())
                    .vehicleType(travel.getVehicleType())
                    .statusType(travel.getStatusType())
                    .build();
            travelDtoList.add(travelDto);
        }
        return travelDtoList;
    }

    /**
     * seyahat(sefer) kapasite kontrolü
     *
     * @param travelRequest
     * @throws GeneralException
     */
    public void travelOtherControl(TravelRequest travelRequest) throws GeneralException {
        if (travelRequest.getCapacity() <= 0){
            throw new GeneralException("travel capacity must be grand zero");
        }
    }

    /**
     * kullanıcının gün içerisinde giriş yapıp yaptığının kontrolü
     *
     * @param loggedUserId
     * @throws GeneralException
     */
    public void loggedUserControl(Long loggedUserId) throws GeneralException {
        LoggedUserDto loggedUserDto = this.iAccountServiceClient.loggedUserControl(loggedUserId).getBody();
        if (loggedUserDto == null){
            throw new GeneralException("logged user record not found with id: " + loggedUserId);
        }
        if (loggedUserDto.getUserDto().getUserType() == UserType.ADMIN){
            throw new GeneralException("cannot use this service because user is admin");
        }
    }

    /**
     * şehir kontrolü
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    public ProvinceDto provinceControl(Long id, Long loggedUserId) throws GeneralException {
        ProvinceDto provinceDto = this.iProvinceServiceClient.getProvince(id ,loggedUserId).getBody();
        if (provinceDto == null){
            throw new GeneralException("province not found with id: " + id);
        }
        return provinceDto;
    }
}
