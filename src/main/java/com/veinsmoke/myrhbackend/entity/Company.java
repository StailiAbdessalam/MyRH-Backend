package com.veinsmoke.myrhbackend.entity;


import com.veinsmoke.myrhbackend.entity.superclass.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column( nullable = false )
    @CreationTimestamp
    LocalDateTime created_at;

    @Column( nullable = false )
    @UpdateTimestamp
    LocalDateTime updated_at;

    @OneToMany(mappedBy = "company")
    List<JobOffer> jobOffers;

    @OneToOne(fetch = FetchType.LAZY)
    SuspendedCompany suspendedCompany;

}
