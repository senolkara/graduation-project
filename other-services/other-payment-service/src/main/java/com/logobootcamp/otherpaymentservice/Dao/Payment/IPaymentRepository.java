package com.logobootcamp.otherpaymentservice.Dao.Payment;

import com.logobootcamp.otherpaymentservice.Entities.Payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
