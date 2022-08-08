package com.logobootcamp.adminprovinceservice.RestAPI;

import com.logobootcamp.adminprovinceservice.Business.Province.IProvinceService;
import com.logobootcamp.commonservice.Dto.Province.ProvinceDto;
import com.logobootcamp.adminprovinceservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.ProvinceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/{loggedUserId}/province")
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
     * bütün şehirler listelenir.
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping
    public ResponseEntity<List<ProvinceDto>> getAllProvince(@PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iProvinceService.getAllProvince(loggedUserId));
    }

    /**
     * yeni şehir ekleme
     *
     * @param provinceRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @PostMapping
    public ResponseEntity<ProvinceDto> createProvince(@RequestBody ProvinceRequest provinceRequest, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iProvinceService.createProvince(provinceRequest, loggedUserId));
    }

    /**
     * şehir detayı
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProvinceDto> getProvince(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iProvinceService.getProvince(id, loggedUserId));
    }

    /**
     * şehir güncelleme
     *
     * id -> şehir id
     *
     * @param id
     * @param provinceRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ProvinceDto> updateProvince(@PathVariable Long id, @RequestBody ProvinceRequest provinceRequest, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iProvinceService.updateProvince(id, provinceRequest, loggedUserId));
    }

    /**
     * şehir sil
     *
     * id -> şehir id
     *
     * @param id
     * @param loggedUserId
     * @throws GeneralException
     */
    @DeleteMapping("/{id}")
    public void deleteProvince(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        this.iProvinceService.deleteProvince(id, loggedUserId);
    }

}
