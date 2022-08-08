package com.logobootcamp.adminpurchaseservice.Dao.Purchase;

import com.logobootcamp.adminpurchaseservice.Entities.Purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByPaymentId(Long paymentId);
}
