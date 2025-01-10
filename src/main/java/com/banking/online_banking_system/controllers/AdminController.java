package com.banking.online_banking_system.controllers;

import com.banking.online_banking_system.entities.Bank;
import com.banking.online_banking_system.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Endpoint to add a bank
    @PostMapping("/banks")
    public ResponseEntity<Bank> addBank(@RequestBody Bank bank) {
        return ResponseEntity.ok(adminService.addBank(bank));
    }

    // Endpoint to get all banks
    @GetMapping("/banks")
    public ResponseEntity<List<Bank>> getAllBanks() {
        return ResponseEntity.ok(adminService.getAllBanks());
    }

    // Endpoint to get a bank by ID
    @GetMapping("/banks/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.getBankById(id));
    }

    // Endpoint to update bank details
    @PutMapping("/banks/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable Long id, @RequestBody Bank bankDetails) {
        return ResponseEntity.ok(adminService.updateBank(id, bankDetails));
    }

    // Endpoint to delete a bank
    @DeleteMapping("/banks/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
        adminService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }
}

