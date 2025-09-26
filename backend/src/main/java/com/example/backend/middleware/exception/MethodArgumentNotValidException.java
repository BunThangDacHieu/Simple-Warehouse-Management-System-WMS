package com.example.backend.middleware.exception;

public class MethodArgumentNotValidException extends RuntimeException {
    private final int statusCode;

    public MethodArgumentNotValidException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
