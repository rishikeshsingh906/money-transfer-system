package com.moneytransfer.service;

import com.moneytransfer.dto.AccountRequestDto;
import com.moneytransfer.dto.AccountResponseDto;
import com.moneytransfer.entity.Account;
import com.moneytransfer.exception.ResourceNotFoundException;
import com.moneytransfer.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moneytransfer.dto.DepositRequestDto;


import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public AccountResponseDto createAccount(AccountRequestDto request) {

        if (accountRepository.existsByAccountNumber(request.accountNumber())) {
            throw new IllegalArgumentException("Account number already exists");
        }

        Account account = new Account(
                request.accountHolderName(),
                request.accountNumber(),
                BigDecimal.ZERO
        );

        Account savedAccount = accountRepository.save(account);

        return convertToDto(savedAccount);
    }

    @Transactional(readOnly = true)
    public AccountResponseDto getAccount(String accountNumber) {

        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found"));

        return convertToDto(account);
    }

    private AccountResponseDto convertToDto(Account account) {

        return new AccountResponseDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getAccountNumber(),
                account.getBalance()
        );
    }

    public AccountResponseDto deposit(
            String accountNumber,
            DepositRequestDto request
    ) {
        Account account = accountRepository
                .findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account not found")
                );

        account.credit(request.getAmount());

        Account savedAccount = accountRepository.save(account);

        return new AccountResponseDto(
                savedAccount.getId(),
                savedAccount.getAccountHolderName(),
                savedAccount.getAccountNumber(),
                savedAccount.getBalance()
        );
    }
}