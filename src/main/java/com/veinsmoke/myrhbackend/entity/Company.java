package com.veinsmoke.myrhbackend.entity;


import com.veinsmoke.myrhbackend.entity.superclass.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company extends User {
    @Column(nullable = false)
    String name;


    String logo;

    @Column(nullable = false)
    int employees;

    @Column(nullable = false)
    LocalDate foundationDate;

    @CreatedDate
    Instant created_at;

    @LastModifiedDate
    Instant updated_at;

    @OneToMany(mappedBy = "company")
    List<JobOffer> jobOffers;

    @OneToOne(fetch = FetchType.LAZY)
    SuspendedCompany suspendedCompany;

}
