package com.example.testpayment.service;

import com.example.testpayment.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User save(User user);

    User create(User user);

    User getByEmail(String email);

    UserDetailsService userDetailsService();

    void logout();
}
