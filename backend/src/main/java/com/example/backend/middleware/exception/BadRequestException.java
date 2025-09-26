package com.example.backend.middleware.exception;

public class BadRequestException extends RuntimeException {

    private final int statusCode;

    public BadRequestException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
