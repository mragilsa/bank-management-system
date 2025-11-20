package com.bank.bank_management.service.impl;

import com.bank.bank_management.dto.UserLoginDTO;
import com.bank.bank_management.dto.UserRegisterDTO;
import com.bank.bank_management.dto.UserResponseDTO;
import com.bank.bank_management.entity.User;
import com.bank.bank_management.repository.TransactionRepository;
import com.bank.bank_management.repository.UserRepository;
import com.bank.bank_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getBalance());
    }

    @Override
    public UserResponseDTO register(UserRegisterDTO dto) {
        if(userRepository.findByEmail(dto.email()).isPresent())
            throw new RuntimeException("Email is already registered");
        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .balance(BigDecimal.ZERO)
                .build();
        return toDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO login(UserLoginDTO dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if(!passwordEncoder.matches(dto.password(), user.getPassword()))
            throw new RuntimeException("Invalid email or password");
        return toDTO(user);
    }

    @Override
    public UserResponseDTO getById(UUID id) {
        return toDTO(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public BigDecimal checkBalance(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid email or password");
        return user.getBalance();
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO adjustBalance(UUID id, String amount) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setBalance(new BigDecimal(amount));
        return toDTO(userRepository.save(user));
    }

    @Override
    public void deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO updateProfile(String email, String password, String name) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("Invalid email or password");
        user.setName(name);
        return toDTO(userRepository.save(user));
    }
}