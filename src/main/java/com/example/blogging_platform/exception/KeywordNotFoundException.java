package com.example.blogging_platform.exception;

public class KeywordNotFoundException extends RuntimeException {
    public KeywordNotFoundException() {
    }

    public KeywordNotFoundException(String message) {
        super(message);
    }

    public KeywordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeywordNotFoundException(Throwable cause) {
        super(cause);
    }

    public KeywordNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
