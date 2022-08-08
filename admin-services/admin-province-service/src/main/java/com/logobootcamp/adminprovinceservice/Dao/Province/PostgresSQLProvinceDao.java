package com.logobootcamp.adminprovinceservice.Dao.Province;

import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import com.logobootcamp.commonservice.Requests.LoggedUserRequest;
import com.logobootcamp.commonservice.Requests.ProvinceRequest;
import com.logobootcamp.adminprovinceservice.Client.IAccountServiceClient;
import com.logobootcamp.adminprovinceservice.Entities.Province.Province;
import com.logobootcamp.adminprovinceservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Primary
public class PostgresSQLProvinceDao implements IProvinceDao {

    private final IProvinceRepository iProvinceRepository;
    private final ModelMapper modelMapper;
    private final IAccountServiceClient iAccountServiceClient;

    /**
     * bütün şehirleri listele
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public List<ProvinceDto> getAllProvince(Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        List<Province> provinceList = this.iProvinceRepository.findAll();
        List<ProvinceDto> provinceDtoList = provinceList.stream().map(province -> modelMapper.map(province, ProvinceDto.class)).collect(Collectors.toList());
        return provinceDtoList;
    }

    /**
     * şehir detayı
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public ProvinceDto getProvince(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Province province = this.iProvinceRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("province not found with id: " + id));
        return modelMapper.map(province, ProvinceDto.class);
    }

    /**
     * şehir ekle
     *
     * @param provinceRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public ProvinceDto createProvince(ProvinceRequest provinceRequest, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        return modelMapper.map(this.iProvinceRepository.save(modelMapper.map(provinceRequest, Province.class)), ProvinceDto.class);
    }

    /**
     * şehir güncelle
     *
     * @param id
     * @param provinceRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @Override
    public ProvinceDto updateProvince(Long id, ProvinceRequest provinceRequest, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Province province = this.iProvinceRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("province not found with id: " + id));
        province.setName(provinceRequest.getName());
        return modelMapper.map(iProvinceRepository.save(province), ProvinceDto.class);
    }

    /**
     * şehir sil
     *
     * @param id
     * @param loggedUserId
     * @throws GeneralException
     */
    @Override
    public void deleteProvince(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Province province = this.iProvinceRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("province not found with id: " + id));
        this.iProvinceRepository.deleteById(province.getId());
    }

    /**
     * kullanıcının gün içerisinde giriş yapıp yaptığının kontrolü
     *
     * @param userId
     * @throws GeneralException
     */
    public void loggedUserControl(Long userId) throws GeneralException {
        LoggedUserDto loggedUserDto = this.iAccountServiceClient.loggedUserControl(userId).getBody();
        if (loggedUserDto == null){
            throw new GeneralException("logged user record not found with id: " + userId);
        }
        if (loggedUserDto.getUserDto().getUserType() != UserType.ADMIN){
            throw new GeneralException("cannot use this service because user is not admin");
        }
    }
}
