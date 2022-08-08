package com.logobootcamp.otherprovinceservice.RestAPI;

import com.logobootcamp.otherprovinceservice.Business.Province.IProvinceService;
import com.logobootcamp.otherprovinceservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/other/province")
@RequiredArgsConstructor
public class ProvinceController {

    /**
     * şehir işlemleri için kullanılan controller.
     *
     * admin kullanıcısı gün içerisinde login olmuş mu olmamış mı kontrolü için
     * api path inden variable olarak loggedUserId yani admin in user id si alınıyor.
     *
     * loggedUserId -> giriş yapmış kullanıcı id si
     *
     */
    private final IProvinceService iProvinceService;

    /**
     * şehir detayı
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/{id}/logged/{loggedUserId}")
    public ResponseEntity<ProvinceDto> getProvince(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iProvinceService.getProvince(id, loggedUserId));
    }
}
