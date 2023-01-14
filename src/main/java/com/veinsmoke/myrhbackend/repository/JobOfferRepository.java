package com.veinsmoke.myrhbackend.repository;

import com.veinsmoke.myrhbackend.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
}

