package com.logobootcamp.adminprovinceservice.Components;

import com.logobootcamp.adminprovinceservice.Dao.Province.IProvinceRepository;
import com.logobootcamp.adminprovinceservice.Entities.Province.Province;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SampleDataRunner implements CommandLineRunner {

    private final IProvinceRepository iProvinceRepository;

    @Override
    public void run(String... args) throws Exception {
        Province topProvince = this.iProvinceRepository.findTopByOrderByIdDesc();
        if (topProvince == null){
            List<Province> provinceList = new ArrayList<>();
            provinceList.add(new Province(1L,"ADANA"));
            provinceList.add(new Province(2L,"ANTALYA"));
            provinceList.add(new Province(3L,"ANKARA"));
            provinceList.add(new Province(4L,"BURSA"));
            provinceList.add(new Province(5L,"DENİZLİ"));
            provinceList.add(new Province(6L,"EDİRNE"));
            provinceList.add(new Province(7L,"ERZURUM"));
            provinceList.add(new Province(8L,"HATAY"));
            provinceList.add(new Province(9L,"GAZİANTEP"));
            provinceList.add(new Province(10L,"GİRESUN"));
            provinceList.add(new Province(11L,"KAYSERİ"));
            provinceList.add(new Province(12L,"KASTAMONU"));
            provinceList.add(new Province(13L,"SİVAS"));
            provinceList.add(new Province(14L,"VAN"));
            provinceList.add(new Province(15L,"ZONGULDAK"));
            this.iProvinceRepository.saveAll(provinceList);
        }
    }
}
