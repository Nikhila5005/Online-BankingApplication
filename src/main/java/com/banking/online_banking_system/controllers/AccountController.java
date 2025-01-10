package com.banking.online_banking_system.controllers;

import com.banking.online_banking_system.entities.Account;
import com.banking.online_banking_system.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

//    @PostMapping("/transfer")
//    public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest) {
//        accountService.transfer(transferRequest.getFromAccountId(), transferRequest.getToAccountId(), transferRequest.getAmount());
//        return ResponseEntity.ok("Transfer successful");
//    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable Long customerId) {
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        return ResponseEntity.ok(accounts);
    }

    // Endpoint for depositing money
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        accountService.deposit(accountId, amount);
        return ResponseEntity.ok("Deposit successful");
    }

    // Endpoint for withdrawing money
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam Long accountId, @RequestParam BigDecimal amount) {
        accountService.withdraw(accountId, amount);
        return ResponseEntity.ok("Withdrawal successful");
    }
}

