package com.logobootcamp.admintravelservice.Dao.Travel;

import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.commonservice.Needs.Enums.StatusType;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import com.logobootcamp.commonservice.Requests.TravelRequest;
import com.logobootcamp.commonservice.Requests.TravelSearchRequest;
import com.logobootcamp.admintravelservice.Client.IAccountServiceClient;
import com.logobootcamp.admintravelservice.Client.IProvinceServiceClient;
import com.logobootcamp.admintravelservice.Entities.Travel.Travel;
import com.logobootcamp.admintravelservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostgresSQLTravelDao implements ITravelDao {

    private final ITravelRepository iTravelRepository;
    private final ModelMapper modelMapper;
    private final IAccountServiceClient iAccountServiceClient;
    private final IProvinceServiceClient iProvinceServiceClient;

    /**
     * bütün seyahatleri(seferleri) listele
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
     * seyahat(sefer) detayı
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
     * seyahat(sefer) ekle
     *
     * @param travelRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public TravelDto createTravel(TravelRequest travelRequest, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        this.travelOtherControl(travelRequest);
        TravelDto travelDto = TravelDto.builder()
                .provinceFrom(this.provinceControl(travelRequest.getProvinceFrom(), loggedUserId))
                .provinceWhere(this.provinceControl(travelRequest.getProvinceWhere(), loggedUserId))
                .amount(travelRequest.getAmount())
                .date(travelRequest.getDate())
                .capacity(travelRequest.getCapacity())
                .vehicleType(travelRequest.getVehicleType())
                .statusType(travelRequest.getStatusType())
                .build();
        this.iTravelRepository.save(modelMapper.map(travelRequest, Travel.class));
        return travelDto;
    }

    /**
     * seyahat(sefer) güncelle
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
        travel.setProvinceFrom(travelRequest.getProvinceFrom());
        travel.setProvinceWhere(travelRequest.getProvinceWhere());
        travel.setAmount(travelRequest.getAmount());
        travel.setDate(travelRequest.getDate());
        travel.setCapacity(travelRequest.getCapacity());
        travel.setVehicleType(travelRequest.getVehicleType());
        travel.setStatusType(travelRequest.getStatusType());
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
     * seyahat(sefer) iptali
     *
     * @param id
     * @param loggedUserId
     * @throws GeneralException
     */
    @Override
    public void deleteTravel(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Travel travel = this.iTravelRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("travel not found with id: " + id));
        travel.setStatusType(StatusType.PASSIVE);
        this.iTravelRepository.save(travel);
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
     * seyahat(sefer) işlemleri için, kapasitenin sıfırdan büyük olması,
     * tarihin bugünün tarihinden ileride olması ve tutarın sıfırdan büyük olması
     * gerektiğinin kontrolleri
     *
     * @param travelRequest
     * @throws GeneralException
     */
    public void travelOtherControl(TravelRequest travelRequest) throws GeneralException {
        if (travelRequest.getCapacity() <= 0){
            throw new GeneralException("travel capacity must be grand zero");
        }
        if (travelRequest.getDate().before(new Date(System.currentTimeMillis()))){
            throw new GeneralException("travel date is before now date");
        }
        if (travelRequest.getAmount() <= 0){
            throw new GeneralException("travel amount must be grand zero");
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
        if (loggedUserDto.getUserDto().getUserType() != UserType.ADMIN){
            throw new GeneralException("cannot use this service because user is not admin");
        }
    }

    /**
     * şehir bilgisinin kontrolü
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    public ProvinceDto provinceControl(Long id, Long loggedUserId) throws GeneralException {
        ProvinceDto provinceDto = this.iProvinceServiceClient.getProvince(id, loggedUserId).getBody();
        if (provinceDto == null){
            throw new GeneralException("province not found with id: " + id);
        }
        return provinceDto;
    }
}
