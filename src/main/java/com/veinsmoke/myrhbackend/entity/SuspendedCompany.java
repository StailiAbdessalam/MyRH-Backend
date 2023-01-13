package com.veinsmoke.myrhbackend.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class SuspendedCompany {
    @Id
    @GeneratedValue( strategy = IDENTITY )
    Long id;

    @Column(nullable = false)
    String reason;

    @OneToOne(fetch = FetchType.LAZY)
    Company company;
}
