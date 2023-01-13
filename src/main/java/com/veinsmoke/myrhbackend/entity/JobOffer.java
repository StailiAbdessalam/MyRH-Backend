package com.veinsmoke.myrhbackend.entity;

import com.veinsmoke.myrhbackend.enums.JobState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

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
    String jobType;

    Double salary;

    JobState state;

    @CreatedDate
    Instant created_at;

    @LastModifiedDate
    Instant updated_at;

    @ManyToOne(fetch = FetchType.LAZY)
    Company company;
}
