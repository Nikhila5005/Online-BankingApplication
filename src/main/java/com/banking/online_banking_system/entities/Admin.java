package com.banking.online_banking_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role = "ADMIN"; // Default role as ADMIN
}
