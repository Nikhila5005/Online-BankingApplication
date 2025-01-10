package com.banking.online_banking_system.services;

import com.banking.online_banking_system.entities.Bank;
import com.banking.online_banking_system.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private BankRepository bankRepository;

    // Method to add a new bank
    public Bank addBank(Bank bank) {
        return bankRepository.save(bank);
    }

    // Method to view all banks
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    // Method to view a specific bank by ID
    public Bank getBankById(Long bankId) {
        return bankRepository.findById(bankId)
                .orElseThrow(() -> new RuntimeException("Bank not found"));
    }

    // Method to update bank details
    public Bank updateBank(Long bankId, Bank bankDetails) {
        Bank bank = getBankById(bankId);
        bank.setBankName(bankDetails.getBankName());
        bank.setAddress(bankDetails.getAddress());
        bank.setContactInfo(bankDetails.getContactInfo());
        return bankRepository.save(bank);
    }

    // Method to delete a bank
    public void deleteBank(Long bankId) {
        bankRepository.deleteById(bankId);
    }
}

