package com.logobootcamp.adminpurchaseservice.RestAPI;

import com.logobootcamp.commonservice.Dto.Buying.BuyingDto;
import com.logobootcamp.adminpurchaseservice.Business.Purchase.IPurchaseService;
import com.logobootcamp.adminpurchaseservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/{loggedUserId}/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    /**
     * satınalma işlemleri için kullanılan controller.
     *
     * admin kullanıcısı gün içerisinde login olmuş mu olmamış mı kontrolü için
     * api path inden variable olarak loggedUserId yani admin in user id si alınıyor.
     *
     * loggedUserId -> giriş yapmış kullanıcı id si
     *
     */
    private final IPurchaseService iPurchaseService;

    /**
     * bütün ssatınalmaları listele
     *
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping
    public ResponseEntity<List<BuyingDto>> getAllPurchase(@PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iPurchaseService.getAllPurchase(loggedUserId));
    }

    /**
     * satınalma detayı
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/{id}")
    public ResponseEntity<BuyingDto> getPurchase(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iPurchaseService.getPurchase(id, loggedUserId));
    }

    /**
     * satınalma iptali
     *
     * @param id
     * @param loggedUserId
     * @throws GeneralException
     */
    @DeleteMapping("/{id}")
    public void deletePurchase(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        this.iPurchaseService.deletePurchase(id, loggedUserId);
    }

}
