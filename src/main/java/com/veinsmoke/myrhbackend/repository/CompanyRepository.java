package com.veinsmoke.myrhbackend.repository;

import com.veinsmoke.myrhbackend.entity.Company;
import com.veinsmoke.myrhbackend.repository.base.UserBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends UserBaseRepository<Company> {
}
