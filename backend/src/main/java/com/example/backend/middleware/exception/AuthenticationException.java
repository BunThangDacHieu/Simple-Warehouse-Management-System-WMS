package com.example.backend.middleware.exception;

public class AuthenticationException extends RuntimeException {

    private final int statusCode;

    public AuthenticationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int statusCode() {
        return statusCode;
    }
}
