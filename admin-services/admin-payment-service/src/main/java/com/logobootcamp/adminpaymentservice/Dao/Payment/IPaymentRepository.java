package com.logobootcamp.adminpaymentservice.Dao.Payment;

import com.logobootcamp.adminpaymentservice.Entities.Payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
