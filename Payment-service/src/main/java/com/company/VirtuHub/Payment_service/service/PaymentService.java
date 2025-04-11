package com.company.VirtuHub.Payment_service.service;

import com.company.VirtuHub.Payment_service.dtos.PaymentRequestDto;
import com.company.VirtuHub.Payment_service.dtos.PaymentResponseDto;
import com.company.VirtuHub.Payment_service.entities.PaymentTransaction;
import com.company.VirtuHub.Payment_service.enums.PaymentStatus;
import com.company.VirtuHub.Payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentResponseDto processPayment(PaymentRequestDto requestDto) {
        log.info("Processing dummy payment for eventId: {} by userId: {}", requestDto.getEventId(), requestDto.getUserId());

        PaymentTransaction transaction = PaymentTransaction.builder()
                .eventId(requestDto.getEventId())
                .userId(requestDto.getUserId())
                .amount(requestDto.getAmount())
                .status(PaymentStatus.SUCCESS)
                .transactionReference(UUID.randomUUID().toString())
                .paymentTime(LocalDateTime.now())
                .isReceiptGenerated(true)
                .PaymentMethod(requestDto.getPaymentMethod())
                .build();

        PaymentTransaction savedTransaction = paymentRepository.save(transaction);

        log.info("Payment successfully processed with transactionId: {}", savedTransaction.getId());

        return PaymentResponseDto.builder()
                .transactionId(savedTransaction.getId())
                .eventId(savedTransaction.getEventId())
                .userId(savedTransaction.getUserId())
                .amount(savedTransaction.getAmount())
                .status(savedTransaction.getStatus().name())
                .paymentMethod(savedTransaction.getPaymentMethod())
                .transactionReference(savedTransaction.getTransactionReference())
                .paymentTime(savedTransaction.getPaymentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .isReceiptGenerated(savedTransaction.getIsReceiptGenerated())
                .build();
    }


    public PaymentResponseDto getPaymentDetails(Long transactionId) {
        log.info("Fetching payment details for transactionId: {}", transactionId);

        PaymentTransaction transaction = paymentRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));

        return PaymentResponseDto.builder()
                .transactionId(transaction.getId())
                .eventId(transaction.getEventId())
                .userId(transaction.getUserId())
                .amount(transaction.getAmount())
                .status(transaction.getStatus().name())
                .paymentMethod(transaction.getPaymentMethod())
                .transactionReference(transaction.getTransactionReference())
                .paymentTime(transaction.getPaymentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .isReceiptGenerated(transaction.getIsReceiptGenerated())
                .build();
    }

    public List<PaymentResponseDto> getPaymentsByUser(Long userId) {
        log.info("Fetching all payments made by userId: {}", userId);

        List<PaymentTransaction> transactions = paymentRepository.findByUserId(userId);

        return transactions.stream()
                .map(tx -> PaymentResponseDto.builder()
                        .transactionId(tx.getId())
                        .eventId(tx.getEventId())
                        .userId(tx.getUserId())
                        .amount(tx.getAmount())
                        .status(tx.getStatus().name())
                        .paymentMethod(tx.getPaymentMethod())
                        .transactionReference(tx.getTransactionReference())
                        .paymentTime(tx.getPaymentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .isReceiptGenerated(tx.getIsReceiptGenerated())
                        .build())
                .toList();
    }


    public List<PaymentResponseDto> getPaymentsByEvent(Long eventId) {
        log.info("Fetching all payments for eventId: {}", eventId);

        List<PaymentTransaction> transactions = paymentRepository.findByEventId(eventId);

        return transactions.stream()
                .map(tx -> PaymentResponseDto.builder()
                        .transactionId(tx.getId())
                        .eventId(tx.getEventId())
                        .userId(tx.getUserId())
                        .amount(tx.getAmount())
                        .status(tx.getStatus().name())
                        .paymentMethod(tx.getPaymentMethod())
                        .transactionReference(tx.getTransactionReference())
                        .paymentTime(tx.getPaymentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .isReceiptGenerated(tx.getIsReceiptGenerated())
                        .build())
                .toList();
    }



}
