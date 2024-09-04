package com.example.testpayment.exception;

import java.util.NoSuchElementException;

public class AccountException extends NoSuchElementException {

    public AccountException(String message) {
        super(message);
    }
}
