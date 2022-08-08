package com.logobootcamp.otherprovinceservice.Dao.Province;

import com.logobootcamp.otherprovinceservice.Client.IAccountServiceClient;
import com.logobootcamp.otherprovinceservice.Entities.Province.Province;
import com.logobootcamp.otherprovinceservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.LoggedUser.LoggedUserDto;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.commonservice.Needs.Enums.UserType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MySQLProvinceDao implements IProvinceDao {

    private final IProvinceRepository iProvinceRepository;
    private final ModelMapper modelMapper;
    private final IAccountServiceClient iAccountServiceClient;

    @Override
    public ProvinceDto getProvince(Long id, Long loggedUserId) throws GeneralException {
        this.loggedUserControl(loggedUserId);
        Province province = this.iProvinceRepository
                .findById(id)
                .orElseThrow(() -> new GeneralException("province not found with id: " + id));
        return modelMapper.map(province, ProvinceDto.class);
    }

    public void loggedUserControl(Long userId) throws GeneralException {
        LoggedUserDto loggedUserDto = this.iAccountServiceClient.loggedUserControl(userId).getBody();
        if (loggedUserDto == null){
            throw new GeneralException("logged user record not found with id: " + userId);
        }
        if (loggedUserDto.getUserDto().getUserType() == UserType.ADMIN){
            throw new GeneralException("cannot use this service because user is admin");
        }
    }
}
