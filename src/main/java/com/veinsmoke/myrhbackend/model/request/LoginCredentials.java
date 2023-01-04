package com.veinsmoke.myrhbackend.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class LoginCredentials {

    @Email
    String email;

    @Size(min = 8)
    @NotBlank
    String password;

}
