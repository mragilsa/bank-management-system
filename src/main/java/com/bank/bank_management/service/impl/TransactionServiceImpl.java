package com.bank.bank_management.service.impl;

import com.bank.bank_management.dto.TransactionDTO;
import com.bank.bank_management.dto.TransactionRequestDTO;
import com.bank.bank_management.entity.Transaction;
import com.bank.bank_management.entity.User;
import com.bank.bank_management.repository.TransactionRepository;
import com.bank.bank_management.repository.UserRepository;
import com.bank.bank_management.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid email or password");
        return user;
    }

    private TransactionDTO toDTO(Transaction t) {
        return new TransactionDTO(
                t.getId(),
                t.getUser().getId(),
                t.getTargetUser() != null ? t.getTargetUser().getId() : null,
                t.getType(),
                t.getAmount(),
                t.getDescription()
        );
    }

    @Override
    public TransactionDTO deposit(TransactionRequestDTO request) {
        User user = authenticate(request.email(), request.password());
        BigDecimal depositAmount = request.amount();
        user.setBalance(user.getBalance().add(depositAmount));
        userRepository.save(user);

        Transaction transaction = Transaction.builder()
                .user(user)
                .type("DEPOSIT")
                .amount(depositAmount)
                .description(request.description())
                .build();
        transactionRepository.save(transaction);

        return toDTO(transaction);
    }

    @Override
    public TransactionDTO withdraw(TransactionRequestDTO request) {
        User user = authenticate(request.email(), request.password());
        BigDecimal withdrawAmount = request.amount();
        if(user.getBalance().compareTo(withdrawAmount) < 0)
            throw new RuntimeException("Insufficient balance");
        user.setBalance(user.getBalance().subtract(withdrawAmount));
        userRepository.save(user);

        Transaction transaction = Transaction.builder()
                .user(user)
                .type("WITHDRAW")
                .amount(withdrawAmount)
                .description(request.description())
                .build();
        transactionRepository.save(transaction);

        return toDTO(transaction);
    }

    @Override
    public TransactionDTO transfer(TransactionRequestDTO request) {
        User sender = authenticate(request.email(), request.password());
        User target = userRepository.findByEmail(request.targetEmail())
                .orElseThrow(() -> new RuntimeException("Target user not found"));
        BigDecimal transferAmount = request.amount();
        if(sender.getBalance().compareTo(transferAmount) < 0)
            throw new RuntimeException("Insufficient balance");

        sender.setBalance(sender.getBalance().subtract(transferAmount));
        target.setBalance(target.getBalance().add(transferAmount));
        userRepository.save(sender);
        userRepository.save(target);

        Transaction transaction = Transaction.builder()
                .user(sender)
                .targetUser(target)
                .type("TRANSFER")
                .amount(transferAmount)
                .description(request.description())
                .build();
        transactionRepository.save(transaction);

        return toDTO(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactions(String email, String password) {
        User user = authenticate(email, password);
        return transactionRepository.findAllByUser(user)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}