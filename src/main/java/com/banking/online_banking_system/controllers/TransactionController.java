package com.banking.online_banking_system.controllers;

import com.banking.online_banking_system.entities.Transactions;
import com.banking.online_banking_system.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    // Endpoint to get transaction history for an account
    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<Transactions>> getTransactionHistory(@PathVariable Long accountId) {
        return ResponseEntity.ok(transactionService.getTransactionHistoryByAccountId(accountId)); // Corrected method call
    }
}

