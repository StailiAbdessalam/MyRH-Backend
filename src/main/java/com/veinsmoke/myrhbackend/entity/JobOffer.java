package com.veinsmoke.myrhbackend.entity;

import com.veinsmoke.myrhbackend.enums.JobState;
import com.veinsmoke.myrhbackend.enums.JobType;
import com.veinsmoke.myrhbackend.enums.SalaryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class JobOffer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(nullable = false)
    String profile;

    @Column(nullable = false)
    String description;

    @Email
    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    JobType jobType;

    Double salary;

    SalaryType salaryType;

    @Column(nullable = false)
    String Country;

    @Column(nullable = false)
    JobState state;

    @Column( nullable = false )
    @CreationTimestamp
    LocalDateTime created_at;

    @Column( nullable = false )
    @UpdateTimestamp
    LocalDateTime updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    Company company;
}
