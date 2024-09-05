package com.example.testpayment.service;

public interface RateLimitService {
    boolean tryConsume();

    Boolean isAvailableTickets();
}
