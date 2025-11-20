package com.bank.bank_management.controller;

import com.bank.bank_management.dto.TransactionDTO;
import com.bank.bank_management.dto.TransactionRequestDTO;
import com.bank.bank_management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public TransactionDTO deposit(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam BigDecimal amount,
            @RequestParam(required = false) String description
    ) {
        TransactionRequestDTO request = new TransactionRequestDTO(email, password, null, amount, description);
        return transactionService.deposit(request);
    }

    @PostMapping("/withdraw")
    public TransactionDTO withdraw(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam BigDecimal amount,
            @RequestParam(required = false) String description
    ) {
        TransactionRequestDTO request = new TransactionRequestDTO(email, password, null, amount, description);
        return transactionService.withdraw(request);
    }

    @PostMapping("/transfer")
    public TransactionDTO transfer(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String targetEmail,
            @RequestParam BigDecimal amount,
            @RequestParam(required = false) String description
    ) {
        TransactionRequestDTO request = new TransactionRequestDTO(email, password, targetEmail, amount, description);
        return transactionService.transfer(request);
    }

    @GetMapping
    public List<TransactionDTO> getTransactions(@RequestParam String email,
                                                @RequestParam String password) {
        return transactionService.getTransactions(email, password);
    }
}