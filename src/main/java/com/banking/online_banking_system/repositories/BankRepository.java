package com.banking.online_banking_system.repositories;

import com.banking.online_banking_system.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByUsername(String username);
}
