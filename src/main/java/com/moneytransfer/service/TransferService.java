package com.moneytransfer.service;

import com.moneytransfer.dto.TransferRequestDto;
import com.moneytransfer.dto.TransferResponseDto;
import com.moneytransfer.entity.Account;
import com.moneytransfer.entity.TransferRecord;
import com.moneytransfer.exception.InsufficientBalanceException;
import com.moneytransfer.exception.ResourceNotFoundException;
import com.moneytransfer.repository.AccountRepository;
import com.moneytransfer.repository.TransferRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransferService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;

    public TransferService(AccountRepository accountRepository,
                           TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    @Transactional
    public TransferResponseDto transferMoney(TransferRequestDto request) {

        if (request.fromAccountNumber().equals(request.toAccountNumber())) {
            throw new IllegalArgumentException(
                    "Sender and receiver accounts cannot be same");
        }

        Account sender = accountRepository
                .findByAccountNumber(request.fromAccountNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sender account not found"));

        Account receiver = accountRepository
                .findByAccountNumber(request.toAccountNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Receiver account not found"));

        if (sender.getBalance().compareTo(request.amount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        sender.debit(request.amount());
        receiver.credit(request.amount());

        accountRepository.save(sender);
        accountRepository.save(receiver);

        TransferRecord record = new TransferRecord(
                sender.getAccountNumber(),
                receiver.getAccountNumber(),
                request.amount(),
                LocalDateTime.now(),
                "SUCCESS"
        );

        TransferRecord savedRecord = transferRepository.save(record);

        return new TransferResponseDto(
                savedRecord.getId(),
                savedRecord.getFromAccountNumber(),
                savedRecord.getToAccountNumber(),
                savedRecord.getAmount(),
                savedRecord.getStatus(),
                savedRecord.getTransferDate()
        );
    }
}