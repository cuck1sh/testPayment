package com.example.testpayment.service.impl;

import com.example.testpayment.exception.AccountException;
import com.example.testpayment.models.User;
import com.example.testpayment.repository.UserRepository;
import com.example.testpayment.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private static final int MAX_LOGIN_ATTEMPTS = 3;

    @Override
    public Boolean isBlocked(String email) {
        User user = getByEmail(email);
        return user.getLockUntil().isAfter(LocalDateTime.now()) && user.getLockUntil() != null;
    }

    @Override
    public void loginFailed(String email) {
        User user = getByEmail(email);
        int currentAttempts = user.getFailedLoginAttempts();
        user.setFailedLoginAttempts(currentAttempts + 1);
        if (user.getFailedLoginAttempts() >= MAX_LOGIN_ATTEMPTS) {
            user.setLockUntil(LocalDateTime.now().plusSeconds(30));
            log.info("Пользователю {} превысил число попыток входа, доступ заблокирован на 30сек", user.getEmail());
        }
    }

    @Override
    public void loginSuccess(String email) {
        User user = getByEmail(email);
        user.setLockUntil(null);
        user.setFailedLoginAttempts(0);
        save(user);
    }

    @Override
    public void logout() {
        User user = getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setLogout(true);
        repository.save(user);
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User create(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            log.info("Пользователь с email {} уже существует", user.getEmail());
            throw new AccountException("Пользователь с таким email уже существует");
        }
        log.info("Создан новый юзер с email {}", user.getEmail());
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
