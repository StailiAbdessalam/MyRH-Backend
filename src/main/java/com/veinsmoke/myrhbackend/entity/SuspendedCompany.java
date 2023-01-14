package com.veinsmoke.myrhbackend.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @Column( nullable = false )
    @CreationTimestamp
    LocalDateTime created_at;

    @Column( nullable = false )
    @UpdateTimestamp
    LocalDateTime updated_at;
}
