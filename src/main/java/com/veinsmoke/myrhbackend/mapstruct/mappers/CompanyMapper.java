package com.veinsmoke.myrhbackend.mapstruct.mappers;

import com.veinsmoke.myrhbackend.entity.Company;
import com.veinsmoke.myrhbackend.mapstruct.dtos.CompanyGetDto;
import com.veinsmoke.myrhbackend.mapstruct.dtos.CompanyPostDto;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface CompanyMapper {

    Company companyPostDtoToCompany(CompanyPostDto companyPostDto);
    CompanyGetDto companyToCompanyGetDto(Company company);

}
