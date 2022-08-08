package com.logobootcamp.othertravelservice.RestAPI;

import com.logobootcamp.commonservice.Dto.Travel.TravelDto;
import com.logobootcamp.commonservice.Requests.TravelRequest;
import com.logobootcamp.commonservice.Requests.TravelSearchRequest;
import com.logobootcamp.othertravelservice.Business.Travel.ITravelService;
import com.logobootcamp.othertravelservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/other/travel")
@RequiredArgsConstructor
public class TravelController {

    /**
     * bireysel veya kurumsal kullanıcıların seyahat(sefer) işlemleri için kullandığı controller.
     *
     * admin kullanıcısı gün içerisinde login olmuş mu olmamış mı kontrolü için
     * api path inden variable olarak loggedUserId yani admin in user id si alınıyor.
     *
     * loggedUserId -> giriş yapmış kullanıcı id si
     *
     */
    private final ITravelService iTravelService;

    /**
     * bireysel veya kurumsal kullanıcıların bütün seyahatleri(seferleri) listelemesi
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/logged/{loggedUserId}")
    public ResponseEntity<List<TravelDto>> getAllTravel(@PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iTravelService.getAllTravel(loggedUserId));
    }

    /**
     * bireysel veya kurumsal kullanıcıların seyahat(sefer) detayını incelemesi
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/{id}/logged/{loggedUserId}")
    public ResponseEntity<TravelDto> getTravel(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iTravelService.getTravel(id, loggedUserId));
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
    @PatchMapping("/{id}/logged/{loggedUserId}")
    public ResponseEntity<TravelDto> updateTravel(@PathVariable Long id, @RequestBody TravelRequest travelRequest, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iTravelService.updateTravel(id, travelRequest, loggedUserId));
    }

    /**
     * şehir bilgisi, tarih, taşıt türüne göre arama
     *
     * @param travelSearchRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/search/logged/{loggedUserId}")
    public ResponseEntity<List<TravelDto>> searchTravel(@RequestBody TravelSearchRequest travelSearchRequest, @PathVariable(required = false) Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iTravelService.searchTravel(travelSearchRequest, loggedUserId));
    }

}
