package com.veinsmoke.myrhbackend.error.handler;

import com.veinsmoke.myrhbackend.error.exception.auth.LoginFailedException;
import com.veinsmoke.myrhbackend.error.exception.auth.RegisterFailedException;
import com.veinsmoke.myrhbackend.error.exception.auth.VerificationFailedException;
import com.veinsmoke.myrhbackend.model.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseStatus
public class AuthExceptionHandler {

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ResponseMessage> loginFailed(LoginFailedException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ResponseMessage(HttpStatus.FORBIDDEN, exception.getMessage()));
    }

    @ExceptionHandler(RegisterFailedException.class)
    public ResponseEntity<ResponseMessage> registerFailed(RegisterFailedException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseMessage(HttpStatus.CONFLICT, exception.getMessage()));
    }





}

