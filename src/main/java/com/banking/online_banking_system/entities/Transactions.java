package com.banking.online_banking_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "from_account_id", nullable = false)
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id", nullable = false)
    private Account toAccount;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String transactionType;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private java.time.LocalDateTime transactionDate;
}
