//package com.example.testpayment.config;
//
//import com.example.testpayment.exception.AuthException;
//import com.example.testpayment.service.ErrorService;
//import com.example.testpayment.service.UserService;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    private final UserService userService;
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json");
//        response.getWriter().write("{\"error\": \"Authentication failed: " + authException.getMessage() + "\"}");
//
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Ошибка аутентификации: " + authException.getMessage());
//    }
//}