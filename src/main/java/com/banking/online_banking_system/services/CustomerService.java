package com.banking.online_banking_system.services;

import com.banking.online_banking_system.entities.Account;
import com.banking.online_banking_system.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmailNotificationService emailNotificationService;

    // Method to perform account transfer
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("From account not found"));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("To account not found"));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Deduct from the sender's account
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        accountRepository.save(fromAccount);

        // Add to the receiver's account
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(toAccount);

        // Send email notifications
        String fromSubject = "Transfer Confirmation";
        String fromBody = "You have transferred " + amount + " to account " + toAccountId +
                ". Your new balance is " + fromAccount.getBalance() + ".";
        emailNotificationService.sendTransactionNotification(fromAccount.getCustomer().getEmail(), fromSubject, fromBody);

        String toSubject = "Funds Received";
        String toBody = "You have received " + amount + " from account " + fromAccountId +
                ". Your new balance is " + toAccount.getBalance() + ".";
        emailNotificationService.sendTransactionNotification(toAccount.getCustomer().getEmail(), toSubject, toBody);
    }
}

