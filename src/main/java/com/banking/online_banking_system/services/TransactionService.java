package com.banking.online_banking_system.services;

import com.banking.online_banking_system.entities.Transactions;
import com.banking.online_banking_system.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Method to log a transaction (for future use)
    public Transactions logTransaction(Transactions transaction) {
        return transactionRepository.save(transaction);
    }

    // Method to get transaction history for a specific account
    public List<Transactions> getTransactionHistoryByAccountId(Long accountId) {
        return transactionRepository.findByFromAccount_AccountIdOrToAccount_AccountId(accountId, accountId);
    }

}

