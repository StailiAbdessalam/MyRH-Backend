package com.veinsmoke.myrhbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.veinsmoke.myrhbackend.entity.superclass.User;
import com.veinsmoke.myrhbackend.enums.CompanySize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


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

    @Column(columnDefinition = "boolean default false")
    Boolean verified;

    String logo;

    @Column(nullable = false)
    CompanySize size;

    @Column(nullable = false)
    LocalDate foundationDate;

    @Column( nullable = false )
    String verificationCode;

    @Column( name = "sent_at" )
    LocalDateTime sentAt;

    @Column( nullable = false, name = "created_at" )
    @CreationTimestamp
    @JsonIgnore
    LocalDateTime createdAt;

    @JsonIgnore
    @Column( nullable = false, name = "updated_at" )
    @UpdateTimestamp
    LocalDateTime updatedAt;

    @OneToMany(mappedBy = "company")
    List<JobOffer> jobOffers;

    @OneToOne(fetch = FetchType.LAZY)
    SuspendedCompany suspendedCompany;

}
