package com.company.VirtuHub.Payment_service.controller;

import com.company.VirtuHub.Payment_service.dtos.PaymentRequestDto;
import com.company.VirtuHub.Payment_service.dtos.PaymentResponseDto;
import com.company.VirtuHub.Payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    // 1. Create Payment (Dummy)
    @PostMapping("/process")
    public ResponseEntity<PaymentResponseDto> processPayment(@RequestBody PaymentRequestDto requestDto) {
        log.info("Processing payment: {}", requestDto);
        return ResponseEntity.ok(paymentService.processPayment(requestDto));
    }

    // 2. Get Payment by Transaction ID
    @GetMapping("/{transactionId}")
    public ResponseEntity<PaymentResponseDto> getPaymentById(@PathVariable Long transactionId) {
        log.info("Fetching payment for transactionId: {}", transactionId);
        return ResponseEntity.ok(paymentService.getPaymentDetails(transactionId));
    }

    // 3. Get All Payments by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponseDto>> getPaymentsByUser(@PathVariable Long userId) {
        log.info("Fetching all payments for userId: {}", userId);
        return ResponseEntity.ok(paymentService.getPaymentsByUser(userId));
    }

    // 4. Get All Payments by Event
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<PaymentResponseDto>> getPaymentsByEvent(@PathVariable Long eventId) {
        log.info("Fetching all payments for eventId: {}", eventId);
        return ResponseEntity.ok(paymentService.getPaymentsByEvent(eventId));
    }
}
