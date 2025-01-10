package com.banking.online_banking_system.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    @Column(unique = true, nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contactInfo;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customers;
}
