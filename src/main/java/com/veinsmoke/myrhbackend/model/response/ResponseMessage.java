package com.veinsmoke.myrhbackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class ResponseMessage {
    HttpStatus status;
    String message;
}
