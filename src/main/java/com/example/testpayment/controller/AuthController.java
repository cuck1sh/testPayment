package com.example.testpayment.controller;

import com.example.testpayment.dto.AuthDto;
import com.example.testpayment.dto.JwtAuthenticationResponse;
import com.example.testpayment.service.AuthenticationService;
import com.example.testpayment.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public JwtAuthenticationResponse signUp(@RequestBody @Valid AuthDto authDto) {
        return authenticationService.signUp(authDto);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid AuthDto authDto) {
        return authenticationService.signIn(authDto);
    }

    @GetMapping("/logout")
    public HttpStatus logout() {
        userService.logout();
        return HttpStatus.OK;
    }
}
