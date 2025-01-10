package com.banking.online_banking_system.services;

import com.banking.online_banking_system.entities.Account;
import com.banking.online_banking_system.entities.Bank;
import com.banking.online_banking_system.entities.Customer;
import com.banking.online_banking_system.repositories.AccountRepository;
import com.banking.online_banking_system.repositories.BankRepository;
import com.banking.online_banking_system.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired

    private CustomerRepository customerRepository;
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private EmailNotificationService emailNotificationService;
    public Account createAccount(Account account) {
        // Ensure customer exists
        Customer customer = customerRepository.findById(account.getCustomer().getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Ensure bank exists
        Bank bank = bankRepository.findById(account.getBank().getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found"));

        // Set the customer and bank in the account
        account.setCustomer(customer);
        account.setBank(bank);

        // Save the account
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = getAccountById(fromAccountId);
        Account toAccount = getAccountById(toAccountId);

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    public List<Account> getAccountsByCustomerId(Long customerId) {
        // Fetch the customer from the repository using the provided customer ID
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found")); // Handle case where customer does not exist

        // Now that we have the customer, we can fetch accounts for this customer
        return accountRepository.findByCustomer(customer);
    }



    // Method for depositing money
public void deposit(Long accountId, BigDecimal amount) {
    Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));

    // Update account balance
    account.setBalance(account.getBalance().add(amount));
    accountRepository.save(account);

    // Send email notification for deposit
    String subject = "Deposit Confirmation";
    String body = "Dear " + account.getCustomer().getName() + ", \n\nYou've successfully deposited " + amount +
            ". Your new balance is " + account.getBalance() + ".\n\nThank you!";
    emailNotificationService.sendTransactionNotification(account.getCustomer().getEmail(), subject, body);
}

// Method for withdrawing money
public void withdraw(Long accountId, BigDecimal amount) {
    Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));

    // Ensure there are enough funds in the account
    if (account.getBalance().compareTo(amount) < 0) {
        throw new RuntimeException("Insufficient funds");
    }

    // Update account balance
    account.setBalance(account.getBalance().subtract(amount));
    accountRepository.save(account);

    // Send email notification for withdrawal
    String subject = "Withdrawal Confirmation";
    String body = "Dear " + account.getCustomer().getName() + ", \n\nYou've successfully withdrawn " + amount +
            ". Your new balance is " + account.getBalance() + ".\n\nThank you!";
    emailNotificationService.sendTransactionNotification(account.getCustomer().getEmail(), subject, body);
}
}

