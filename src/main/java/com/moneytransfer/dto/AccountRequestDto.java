package com.moneytransfer.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountRequestDto(

        @NotBlank(message = "Account holder name is required")
        String accountHolderName,

        @NotBlank(message = "Account number is required")
        String accountNumber
) {
}