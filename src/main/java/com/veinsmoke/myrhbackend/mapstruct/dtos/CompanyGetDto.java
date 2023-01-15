package com.veinsmoke.myrhbackend.mapstruct.dtos;


import com.veinsmoke.myrhbackend.enums.CompanySize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyGetDto {
    private String email;
    private String logo;
    private String name;
    private CompanySize size;
    private String foundationDate;
}
