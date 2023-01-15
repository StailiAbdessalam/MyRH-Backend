package com.veinsmoke.myrhbackend.error.exception.auth;

public class VerificationFailedException extends RuntimeException{
    public VerificationFailedException(String message) {
        super(message);
    }
}
