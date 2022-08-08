package com.logobootcamp.adminprovinceservice.Dao.Province;

import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.commonservice.Dto.User.UserDto;
import com.logobootcamp.commonservice.Requests.LoggedUserRequest;
import com.logobootcamp.commonservice.Requests.ProvinceRequest;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import com.logobootcamp.adminprovinceservice.Client.IAccountServiceClient;
import com.logobootcamp.adminprovinceservice.Entities.Province.Province;
import com.logobootcamp.adminprovinceservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MySQLProvinceDao implements IProvinceDao {

    private final IProvinceRepository iProvinceRepository;
    private final ModelMapper modelMapper;
    private final IAccountServiceClient iAccountServiceClient;

    @Override
    public List<ProvinceDto> getAllProvince(Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        List<Province> provinceList = this.iProvinceRepository.findAll();
        List<ProvinceDto> provinceDtoList = provinceList.stream().map(province -> modelMapper.map(province, ProvinceDto.class)).collect(Collectors.toList());
        return provinceDtoList;
    }

    @Override
    public ProvinceDto getProvince(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Province province = this.iProvinceRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("province not found with id: " + id));
        return modelMapper.map(province, ProvinceDto.class);
    }

    @Override
    public ProvinceDto createProvince(ProvinceRequest provinceRequest, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        return modelMapper.map(this.iProvinceRepository.save(modelMapper.map(provinceRequest, Province.class)), ProvinceDto.class);
    }

    @Override
    public ProvinceDto updateProvince(Long id, ProvinceRequest provinceRequest, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Province province = this.iProvinceRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("province not found with id: " + id));
        province.setName(provinceRequest.getName());
        return modelMapper.map(iProvinceRepository.save(province), ProvinceDto.class);
    }

    @Override
    public void deleteProvince(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Province province = this.iProvinceRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("province not found with id: " + id));
        this.iProvinceRepository.deleteById(province.getId());
    }

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
