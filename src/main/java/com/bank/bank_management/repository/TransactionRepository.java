package com.bank.bank_management.repository;

import com.bank.bank_management.entity.Transaction;
import com.bank.bank_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByUser(User user);
}