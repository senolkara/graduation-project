package com.logobootcamp.admintravelservice.RestAPI;

import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.commonservice.Requests.LoggedUserRequest;
import com.logobootcamp.commonservice.Requests.TravelRequest;
import com.logobootcamp.commonservice.Requests.TravelSearchRequest;
import com.logobootcamp.admintravelservice.Business.Travel.ITravelService;
import com.logobootcamp.admintravelservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/{loggedUserId}/travel")
@RequiredArgsConstructor
public class TravelController {

    /**
     * seyahat(sefer) işlemleri için kullanılan controller.
     *
     * admin kullanıcısı gün içerisinde login olmuş mu olmamış mı kontrolü için
     * api path inden variable olarak loggedUserId yani admin in user id si alınıyor.
     *
     * loggedUserId -> giriş yapmış kullanıcı id si
     *
     */
    private final ITravelService iTravelService;

    /**
     * bütün seyahatleri(seferleri) listele
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping
    public ResponseEntity<List<TravelDto>> getAllTravel(@PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iTravelService.getAllTravel(loggedUserId));
    }

    /**
     * seyahat(sefer) ekle
     *
     * @param travelRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @PostMapping
    public ResponseEntity<TravelDto> createTravel(@RequestBody TravelRequest travelRequest, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iTravelService.createTravel(travelRequest, loggedUserId));
    }

    /**
     * seyahat(sefer) detayı
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/{id}")
    public ResponseEntity<TravelDto> getTravel(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iTravelService.getTravel(id, loggedUserId));
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
    @PatchMapping("/{id}")
    public ResponseEntity<TravelDto> updateTravel(@PathVariable Long id, @RequestBody TravelRequest travelRequest, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iTravelService.updateTravel(id, travelRequest, loggedUserId));
    }

    /**
     * seyahat(sefer) iptali
     *
     * @param id
     * @param loggedUserId
     * @throws GeneralException
     */
    @DeleteMapping("/{id}")
    public void deleteTravel(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        this.iTravelService.deleteTravel(id, loggedUserId);
    }

    /**
     * şehir bilgisi, tarih, taşıt türüne göre arama
     *
     * @param travelSearchRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/search")
    public ResponseEntity<List<TravelDto>> searchTravel(@RequestBody TravelSearchRequest travelSearchRequest, @PathVariable(required = false) Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iTravelService.searchTravel(travelSearchRequest, loggedUserId));
    }

}
