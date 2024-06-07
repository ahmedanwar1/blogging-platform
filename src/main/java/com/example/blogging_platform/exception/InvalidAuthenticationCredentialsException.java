package com.example.blogging_platform.exception;

public class InvalidAuthenticationCredentialsException extends RuntimeException {
    public InvalidAuthenticationCredentialsException() {
    }

    public InvalidAuthenticationCredentialsException(String message) {
        super(message);
    }

    public InvalidAuthenticationCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAuthenticationCredentialsException(Throwable cause) {
        super(cause);
    }

    public InvalidAuthenticationCredentialsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
