package com.banking.online_banking_system.repositories;

import com.banking.online_banking_system.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findByFromAccount_AccountId(Long fromAccountId);

    List<Transactions> findByToAccount_AccountId(Long toAccountId);

    List<Transactions> findByFromAccount_AccountIdAndTransactionDateBetween(Long fromAccountId, LocalDateTime startDate, LocalDateTime endDate);

    List<Transactions> findByToAccount_AccountIdAndTransactionDateBetween(Long toAccountId, LocalDateTime startDate, LocalDateTime endDate);
    // New method to find all transactions related to a specific account
    List<Transactions> findByFromAccount_AccountIdOrToAccount_AccountId(Long fromAccountId, Long toAccountId);
}
