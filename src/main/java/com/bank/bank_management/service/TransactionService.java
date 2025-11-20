package com.bank.bank_management.service;

import com.bank.bank_management.dto.TransactionDTO;
import com.bank.bank_management.dto.TransactionRequestDTO;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    TransactionDTO deposit(TransactionRequestDTO request);
    TransactionDTO withdraw(TransactionRequestDTO request);
    TransactionDTO transfer(TransactionRequestDTO request);
    List<TransactionDTO> getTransactions(String email, String password);
}