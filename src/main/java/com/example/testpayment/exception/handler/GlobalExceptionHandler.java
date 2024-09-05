package com.example.testpayment.exception.handler;

import com.example.testpayment.exception.AuthException;
import com.example.testpayment.exception.ErrorResponseBody;
import com.example.testpayment.service.ErrorService;
import com.example.testpayment.service.RateLimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorService errorService;
    private final RateLimitService rateLimitService;

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorResponseBody> internalAuthenticationServiceException(InternalAuthenticationServiceException ex) {
        return new ResponseEntity<>(errorService.makeResponse(ex), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseBody> authenticationException(AuthException ex) {
        return new ResponseEntity<>(errorService.makeResponse(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseBody> noSuchElementException(NoSuchElementException ex) {
        return new ResponseEntity<>(errorService.makeResponse(ex), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(errorService.makeResponse(ex.getBindingResult()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseBody> badCredentialsException() {
        return new ResponseEntity<>(errorService.makeResponse(new AuthException("Неправильный логин или пароль")), HttpStatus.UNAUTHORIZED);
    }
}
