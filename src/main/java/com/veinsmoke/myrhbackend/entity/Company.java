package com.veinsmoke.myrhbackend.entity;


import com.veinsmoke.myrhbackend.entity.superclass.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;

@Entity
public class Company extends User {
    @Column(nullable = false)
    String name;

    @Column(nullable = true)
    String logo;

    int employees;

    LocalDate foundationDate;

    @CreatedDate
    Instant created_at;

    @LastModifiedDate
    Instant updated_at;
}
