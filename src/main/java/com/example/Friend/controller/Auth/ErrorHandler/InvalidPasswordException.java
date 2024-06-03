package com.example.Friend.controller.Auth.ErrorHandler;

public class InvalidPasswordException extends RuntimeException {
    public  InvalidPasswordException(String message) {
        super(message);
    }
}
