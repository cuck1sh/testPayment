package com.example.testpayment.service;

import com.example.testpayment.dto.AuthDto;
import com.example.testpayment.dto.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signUp(AuthDto request);

    JwtAuthenticationResponse signIn(AuthDto request);
}
