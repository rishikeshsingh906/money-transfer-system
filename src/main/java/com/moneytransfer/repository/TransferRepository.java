package com.moneytransfer.repository;

import com.moneytransfer.entity.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferRepository extends JpaRepository<TransferRecord, Long> {

    List<TransferRecord> findByFromAccountNumberOrToAccountNumberOrderByTransferDateDesc(
            String fromAccountNumber,
            String toAccountNumber
    );
}