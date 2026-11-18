package com.moneytransfer.dto;

import java.math.BigDecimal;

public record AccountResponseDto(
        Long id,
        String accountHolderName,
        String accountNumber,
        BigDecimal balance
) {
}