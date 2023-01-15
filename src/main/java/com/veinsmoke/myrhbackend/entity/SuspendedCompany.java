package com.veinsmoke.myrhbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
