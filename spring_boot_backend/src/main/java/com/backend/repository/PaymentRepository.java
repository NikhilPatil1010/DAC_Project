package com.backend.repository;



import com.backend.entity.Order;
import com.backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByOrderOrderId(Long orderId);
    Optional<Payment> findByOrder(Order order);
    List<Payment> findByPaymentStatus(Payment.PaymentStatus status);
}