package com.example.blogging_platform.exception;

public class GeneralDatabaseException extends RuntimeException {
    public GeneralDatabaseException() {
    }

    public GeneralDatabaseException(String message) {
        super(message);
    }

    public GeneralDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public GeneralDatabaseException(Throwable cause) {
        super(cause);
    }

    public GeneralDatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
