package com.bank.bank_management.dto;

import java.math.BigDecimal;

public record TransactionRequestDTO(
        String email,
        String password,
        String targetEmail,
        BigDecimal amount,
        String description
) {}