package com.company.VirtuHub.Payment_service.repository;

import com.company.VirtuHub.Payment_service.entities.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentTransaction,Long> {


    Optional<PaymentTransaction> findByPaymentId(String paymentId);

    List<PaymentTransaction> findByUserId(Long userId);

    List<PaymentTransaction> findByEventId(Long eventId);
}
