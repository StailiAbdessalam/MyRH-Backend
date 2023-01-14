package com.veinsmoke.myrhbackend.repository;

import com.veinsmoke.myrhbackend.entity.SuspendedCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuspendedCompanyRepository extends JpaRepository<SuspendedCompany, Long> {
}
