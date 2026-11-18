package com.moneytransfer.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_records")
public class TransferRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromAccountNumber;

    private String toAccountNumber;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    private LocalDateTime transferDate;

    private String status;

    public TransferRecord() {
    }

    public TransferRecord(String fromAccountNumber,
                          String toAccountNumber,
                          BigDecimal amount,
                          LocalDateTime transferDate,
                          String status) {
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.transferDate = transferDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public String getStatus() {
        return status;
    }
}