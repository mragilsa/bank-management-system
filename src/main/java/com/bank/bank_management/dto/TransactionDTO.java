package com.bank.bank_management.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDTO(
        UUID id,
        UUID userId,
        UUID targetUserId,
        String type, // DEPOSIT | WITHDRAW | TRANSFER
        BigDecimal amount,
        String description
) {}