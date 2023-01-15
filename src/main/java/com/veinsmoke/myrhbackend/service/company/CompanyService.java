package com.veinsmoke.myrhbackend.service.company;

import com.veinsmoke.myrhbackend.entity.Company;

import java.util.Optional;

public interface CompanyService {
    Optional<Company> getCompanyByEmail(String email);
    void save(Company company);
}
