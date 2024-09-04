package com.example.testpayment.service.impl;

import com.example.testpayment.exception.AccountException;
import com.example.testpayment.models.User;
import com.example.testpayment.repository.UserRepository;
import com.example.testpayment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User create(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new AccountException("Пользователь с таким email уже существует");
        }
        return save(user);
    }

    @Override
    public User getByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new AccountException("Пользователь не найден"));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByEmail;
    }

    @Override
    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByEmail(username);
    }
}
