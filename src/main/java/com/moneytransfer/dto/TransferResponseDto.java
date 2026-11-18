package com.moneytransfer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferResponseDto(
        Long transferId,
        String fromAccountNumber,
        String toAccountNumber,
        BigDecimal amount,
        String status,
        LocalDateTime transferDate
) {
}