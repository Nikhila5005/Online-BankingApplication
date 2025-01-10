package com.banking.online_banking_system.services;

import com.banking.online_banking_system.entities.Bank;
import com.banking.online_banking_system.entities.Customer;
import com.banking.online_banking_system.repositories.AccountRepository;
import com.banking.online_banking_system.repositories.BankRepository;
import com.banking.online_banking_system.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankRepository bankRepository;

    // Method to register a new customer
    public Customer registerCustomer(Customer customer) {
        // Find the bank by ID
        Bank bank = bankRepository.findById(customer.getBank().getBankId())
                .orElseThrow(() -> new RuntimeException("Bank not found")); // Handle case where bank does not exist

        customer.setBank(bank); // Set the bank for the customer
        return customerRepository.save(customer);
    }

    // Method to get all customers of a bank
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Method to get customer by ID
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
}

