package com.example.testpayment.service;

import com.example.testpayment.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User save(User user);

    User create(User user);

    User getByEmail(String email);

    UserDetailsService userDetailsService();

    User getCurrentUser();

    Boolean isBlocked(String email);

    void loginFailed(String email);

    void loginSuccess(String email);

    void logout();
}
