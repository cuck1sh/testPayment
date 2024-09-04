package com.example.testpayment.service;


import com.example.testpayment.dto.PaymentResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface PaymentService {

    ResponseEntity<PaymentResultDto> makePayment(Authentication auth);
}
