package com.logobootcamp.otherpurchaseservice.RestAPI;

import com.logobootcamp.commonservice.Dto.Buying.BuyingDto;
import com.logobootcamp.otherpurchaseservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.BuyingRequest;
import com.logobootcamp.otherpurchaseservice.Business.Purchase.IPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/other/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    /**
     * bireysel veya kurumsal kullanıcıların satınalma işlemleri için kullanıldığı servis.
     *
     * admin kullanıcısı gün içerisinde login olmuş mu olmamış mı kontrolü için
     * api path inden variable olarak loggedUserId yani admin in user id si alınıyor.
     *
     * loggedUserId -> giriş yapmış kullanıcı id si
     *
     */
    private final IPurchaseService iPurchaseService;

    /**
     * bireysel veya kurumsal kullanıcıların yapmış oldukları satınalmalar
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/logged/{loggedUserId}")
    public ResponseEntity<List<BuyingDto>> getAllPurchase(@PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iPurchaseService.getAllPurchase(loggedUserId));
    }

    /**
     * bireysel veya kurumsal kullanıcıların satınalma gerçekleştirmesi
     *
     * @param buyingRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @PostMapping("/logged/{loggedUserId}")
    public ResponseEntity<BuyingDto> createPurchase(@RequestBody BuyingRequest buyingRequest, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iPurchaseService.createPurchase(buyingRequest, loggedUserId));
    }

    /**
     * bireysel veya kurumsal kullanıcıların yapmış oldukları satınalmanın detayı
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/{id}/logged/{loggedUserId}")
    public ResponseEntity<BuyingDto> getPurchase(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iPurchaseService.getPurchase(id ,loggedUserId));
    }

}
