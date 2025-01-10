package com.banking.online_banking_system.repositories;

import com.banking.online_banking_system.entities.Account;
import com.banking.online_banking_system.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomer_CustomerId(Long customerId);

    List<Account> findByBank_BankId(Long bankId);

    Account findByAccountNumber(String accountNumber);
    List<Account> findByCustomer(Customer customer);

}

