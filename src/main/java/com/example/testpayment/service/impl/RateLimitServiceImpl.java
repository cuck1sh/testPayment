package com.example.testpayment.service.impl;

import com.example.testpayment.service.RateLimitService;
import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimitServiceImpl implements RateLimitService {
    public static final Integer LIMIT_ATTEMPTS = 5;


    private final Bucket bucket = Bucket.builder()
            .addLimit(limit -> limit.capacity(LIMIT_ATTEMPTS).refillGreedy(1, Duration.ofSeconds(10)))
            .build();


    @Override
    public boolean tryConsume() {
        return bucket.tryConsume(1);
    }

    @Override
    public Boolean isAvailableTickets() {
        return bucket.getAvailableTokens() > 0;
    }
}