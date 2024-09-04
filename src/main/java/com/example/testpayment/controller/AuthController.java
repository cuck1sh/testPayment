package com.example.testpayment.controller;

import com.example.testpayment.dto.AuthDto;
import com.example.testpayment.dto.JwtAuthenticationResponse;
import com.example.testpayment.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid AuthDto authDto) {
        return authenticationService.signUp(authDto);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid AuthDto authDto) {
        return authenticationService.signIn(authDto);
    }
}
