package com.veinsmoke.myrhbackend.mapstruct.dtos;


import com.veinsmoke.myrhbackend.enums.CompanySize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyPostDto {

    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min = 8)
    private String password;
    private String logo;
    @NotNull
    private String name;
    @NotNull
    private CompanySize size;
    @NotNull
    private String foundationDate;
}
