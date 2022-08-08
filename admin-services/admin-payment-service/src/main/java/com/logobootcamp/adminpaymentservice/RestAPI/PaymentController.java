package com.logobootcamp.adminpaymentservice.RestAPI;

import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.adminpaymentservice.Business.Payment.IPaymentService;
import com.logobootcamp.adminpaymentservice.ExceptionHandling.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/{loggedUserId}/payment")
@RequiredArgsConstructor
public class PaymentController {

    /**
     * ödeme işlemleri için kullanılan controller.
     *
     * admin kullanıcısı gün içerisinde login olmuş mu olmamış mı kontrolü için
     * api path inden variable olarak loggedUserId yani admin in user id si alınıyor.
     *
     * loggedUserId -> giriş yapmış kullanıcı id si
     *
     */
    private final IPaymentService iPaymentService;

    /**
     * ödeme detayı
     *
     * @param id
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iPaymentService.getPayment(id, loggedUserId));
    }

    /**
     * ödeme iptali
     *
     * @param id
     * @param loggedUserId
     * @throws GeneralException
     */
    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        this.iPaymentService.deletePayment(id, loggedUserId);
    }

}
