package com.example.testpayment.service.impl;

import com.example.testpayment.dto.PaymentResultDto;
import com.example.testpayment.exception.AccountException;
import com.example.testpayment.models.Payment;
import com.example.testpayment.models.User;
import com.example.testpayment.repository.PaymentRepository;
import com.example.testpayment.service.PaymentService;
import com.example.testpayment.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private static final BigDecimal PAYMENT_VALUE = BigDecimal.valueOf(1.10);

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ResponseEntity<PaymentResultDto> makePayment(Authentication auth) {
        User user = userService.getByEmail(SecurityContextHolder
                .getContext().getAuthentication().getName());

        if (user.getBalance().compareTo(new BigDecimal(String.valueOf(PAYMENT_VALUE))) >= 0) {
            user.setBalance(user.getBalance().subtract(new BigDecimal(String.valueOf(PAYMENT_VALUE))));

            Payment newPayment = Payment.builder()
                    .user(user)
                    .amount(PAYMENT_VALUE)
                    .timestamp(LocalDateTime.now())
                    .build();

            paymentRepository.saveAndFlush(newPayment);
            userService.save(user);
            log.info("Пользователь {} совершил платеж, текущий баланс = {}", user.getEmail(), user.getBalance());

            return ResponseEntity.ok().body(PaymentResultDto.builder()
                    .balance(user.getBalance())
                    .paymentAmount(newPayment.getAmount())
                    .timestamp(newPayment.getTimestamp())
                    .build());
        } else {
            throw new AccountException("Недостаточно средств на балансе");
        }
    }
}
