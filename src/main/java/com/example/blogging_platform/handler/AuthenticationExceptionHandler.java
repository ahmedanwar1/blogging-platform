package com.example.blogging_platform.handler;

import com.example.blogging_platform.dto.ErrorResponse;
import com.example.blogging_platform.exception.InvalidAuthenticationCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class AuthenticationExceptionHandler {
    @ExceptionHandler(InvalidAuthenticationCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAuthenticationCredentialsException(InvalidAuthenticationCredentialsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                List.of()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
