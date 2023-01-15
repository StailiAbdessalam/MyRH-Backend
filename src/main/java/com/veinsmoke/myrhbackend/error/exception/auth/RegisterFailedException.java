package com.veinsmoke.myrhbackend.error.exception.auth;

public class RegisterFailedException extends RuntimeException{
    public RegisterFailedException(String message) {
        super(message);
    }
}

