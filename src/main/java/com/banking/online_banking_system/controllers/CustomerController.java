package com.banking.online_banking_system.controllers;

import com.banking.online_banking_system.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Endpoint for account transfer
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromAccountId,
                                           @RequestParam Long toAccountId,
                                           @RequestParam BigDecimal amount) {
        customerService.transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok("Transfer successful");
    }
}

