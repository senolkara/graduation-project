package com.logobootcamp.otherpaymentservice.RestAPI;

import com.logobootcamp.commonservice.Dto.Payment.PaymentDto;
import com.logobootcamp.otherpaymentservice.ExceptionHandling.GeneralException;
import com.logobootcamp.commonservice.Requests.BuyingRequest;
import com.logobootcamp.otherpaymentservice.Business.Payment.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/other/payment")
@RequiredArgsConstructor
public class PaymentController {

    /**
     * bireysel veya kurumsal kullanıcıların ödeme işlemleri için kullanıldığı controller.
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
    @GetMapping("/{id}/logged/{loggedUserId}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable Long id, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iPaymentService.getPayment(id, loggedUserId));
    }

    /**
     * ödeme oluşturma
     *
     * @param buyingRequest
     * @param loggedUserId
     * @return
     * @throws GeneralException
     */
    @PostMapping("/logged/{loggedUserId}")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody BuyingRequest buyingRequest, @PathVariable Long loggedUserId) throws GeneralException {
        return ResponseEntity.ok(this.iPaymentService.createPayment(buyingRequest, loggedUserId));
    }

}
