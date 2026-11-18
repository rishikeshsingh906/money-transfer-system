package com.moneytransfer.controller;

import com.moneytransfer.dto.TransferRequestDto;
import com.moneytransfer.dto.TransferResponseDto;
import com.moneytransfer.entity.TransferRecord;
import com.moneytransfer.repository.TransferRepository;
import com.moneytransfer.service.TransferService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;
    private final TransferRepository transferRepository;

    public TransferController(TransferService transferService,
                              TransferRepository transferRepository) {
        this.transferService = transferService;
        this.transferRepository = transferRepository;
    }

    @PostMapping
    public ResponseEntity<TransferResponseDto> transferMoney(
            @Valid @RequestBody TransferRequestDto request) {

        return ResponseEntity.ok(
                transferService.transferMoney(request));
    }

    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<List<TransferRecord>> getHistory(
            @PathVariable String accountNumber) {

        return ResponseEntity.ok(
                transferRepository
                        .findByFromAccountNumberOrToAccountNumberOrderByTransferDateDesc(
                                accountNumber, accountNumber));
    }
}