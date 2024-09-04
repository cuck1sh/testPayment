package com.example.testpayment.controller;

import com.example.testpayment.dto.PaymentResultDto;
import com.example.testpayment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/payment")
    public ResponseEntity<PaymentResultDto> makePayment(Authentication auth) {
        return paymentService.makePayment(auth);
    }
}
