package com.veinsmoke.myrhbackend.service.company;

import com.veinsmoke.myrhbackend.entity.Company;
import com.veinsmoke.myrhbackend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImp implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Optional<Company> getCompanyByEmail(String email){
        return companyRepository.findByEmail(email);
    }

    @Override
    public void save(Company company) {
        companyRepository.save(company);
    }
}

