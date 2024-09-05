//package com.example.testpayment.config;
//
//import com.example.testpayment.exception.AccountException;
//import com.example.testpayment.exception.AuthException;
//import com.example.testpayment.service.UserService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
//    private final UserService userService;
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        AuthenticationException exception) {
//        throw new AuthException("Неправильный логин или пароль");
//    }
//}
