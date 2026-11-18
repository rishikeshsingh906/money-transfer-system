package com.moneytransfer.controller;

import com.moneytransfer.dto.AccountRequestDto;
import com.moneytransfer.dto.AccountResponseDto;
import com.moneytransfer.service.AccountService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.moneytransfer.dto.DepositRequestDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(
            @Valid @RequestBody AccountRequestDto request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(accountService.createAccount(request));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponseDto> getAccount(
            @PathVariable String accountNumber) {

        return ResponseEntity.ok(
                accountService.getAccount(accountNumber));
    }
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<AccountResponseDto> deposit(
            @PathVariable String accountNumber,
            @Valid @RequestBody DepositRequestDto request
    ) {
        return ResponseEntity.ok(
                accountService.deposit(accountNumber, request)
        );
    }
}