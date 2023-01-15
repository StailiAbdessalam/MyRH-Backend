package com.veinsmoke.myrhbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.veinsmoke.myrhbackend.enums.JobState;
import com.veinsmoke.myrhbackend.enums.JobType;
import com.veinsmoke.myrhbackend.enums.SalaryType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobOffer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(nullable = false)
    String profile;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    JobType jobType;

    Double salary;

    SalaryType salaryType;

    @Column(nullable = false)
    String country;

    @Column(nullable = false, columnDefinition = "text default 'OPEN'")
    JobState state;

    @Column( nullable = false )
    @CreationTimestamp
    @JsonIgnore
    LocalDateTime created_at;

    @Column( nullable = false )
    @UpdateTimestamp
    @JsonIgnore
    LocalDateTime updated_at;

    @ManyToOne()
    Company company;
}
