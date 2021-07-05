package com.example.springdataintro.exceptions;

public class InvalidAccountOperationException extends RuntimeException {
    public InvalidAccountOperationException() {
        super();
    }

    public InvalidAccountOperationException(String message) {
        super(message);
    }

    public InvalidAccountOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAccountOperationException(Throwable cause) {
        super(cause);
    }

    protected InvalidAccountOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
