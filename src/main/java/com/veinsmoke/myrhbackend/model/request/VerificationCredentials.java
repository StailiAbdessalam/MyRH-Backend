package com.veinsmoke.myrhbackend.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VerificationCredentials {

    @Email
    String email;
    @Size(min = 8)
    String password;
    @Size(min = 6, max = 6)
    String code;

}
