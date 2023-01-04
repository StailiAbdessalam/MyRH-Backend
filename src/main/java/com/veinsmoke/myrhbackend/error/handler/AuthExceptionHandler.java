package com.veinsmoke.myrhbackend.error.handler;

import com.veinsmoke.myrhbackend.error.exception.auth.LoginFailedException;
import com.veinsmoke.myrhbackend.model.response.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus
public class AuthExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorMessage> loginFailed(LoginFailedException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorMessage(HttpStatus.FORBIDDEN, exception.getMessage()));
    }

}

