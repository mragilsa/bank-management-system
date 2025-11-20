package com.bank.bank_management.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Transaction> transactions;
}