package com.example.Friend.controller.Auth.ErrorHandler;

public class EmailNotFoundException extends RuntimeException {

    public  EmailNotFoundException(String message) {
        super(message);
    }
}
