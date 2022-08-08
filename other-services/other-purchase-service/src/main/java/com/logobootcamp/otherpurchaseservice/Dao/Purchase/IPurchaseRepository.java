package com.logobootcamp.otherpurchaseservice.Dao.Purchase;

import com.logobootcamp.otherpurchaseservice.Entities.Purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {
    Long countAllByUserIdAndTravelId(Long userId, Long travelId);
    List<Purchase> findAllByUserId(Long userId);
}
