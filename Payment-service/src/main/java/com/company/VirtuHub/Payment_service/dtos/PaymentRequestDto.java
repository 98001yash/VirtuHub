package com.company.VirtuHub.Payment_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequestDto {

    private Long eventId;
    private Long userId;
    private BigDecimal amount;
    private String paymentMethod;
}
