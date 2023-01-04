package com.veinsmoke.myrhbackend.error.exception.auth;

public class LoginFailedException  extends RuntimeException{
    public LoginFailedException(String message) {
        super(message);
    }
}

