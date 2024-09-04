package com.example.testpayment.service.impl;

import com.example.testpayment.dto.AuthDto;
import com.example.testpayment.dto.JwtAuthenticationResponse;
import com.example.testpayment.models.User;
import com.example.testpayment.service.AuthenticationService;
import com.example.testpayment.service.JwtService;
import com.example.testpayment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private static final BigDecimal START_CASH = BigDecimal.valueOf(8.00);


    @Override
    public JwtAuthenticationResponse signUp(AuthDto request) {

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .balance(START_CASH)
                .failedLoginAttempts(0)
                .build();

        userService.create(user);

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public JwtAuthenticationResponse signIn(AuthDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        UserDetails user = userService
                .userDetailsService()
                .loadUserByUsername(request.getEmail());

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }
}
