//package com.example.testpayment.util;
//
//import com.example.testpayment.service.RateLimitService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//
//
//@Slf4j
//public class RateLimitingAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final RateLimitService rateLimitService;
//
//
//    public RateLimitingAuthenticationFilter(AuthenticationManager authenticationManager, RateLimitService rateLimitService) {
//        super.setAuthenticationManager(authenticationManager);
//        this.rateLimitService = rateLimitService;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(@NonNull HttpServletRequest request,
//                                                @NonNull HttpServletResponse response) throws AuthenticationException {
//        if (!rateLimitService.tryConsume()) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            try {
//                response.getWriter().write("Too many attempts. Please try again after 30sec");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//        return super.attemptAuthentication(request, response);
//    }
//}
//
