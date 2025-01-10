package com.banking.online_banking_system.controllers;

import com.banking.online_banking_system.entities.Customer;
import com.banking.online_banking_system.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    // Endpoint to register a new customer
    @PostMapping("/customers")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(bankService.registerCustomer(customer));
    }

    // Endpoint to get all customers
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(bankService.getAllCustomers());
    }

    // Endpoint to get customer by ID
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(bankService.getCustomerById(id));
    }
}

