package com.veinsmoke.myrhbackend.service.joboffer;

import com.veinsmoke.myrhbackend.entity.JobOffer;
import com.veinsmoke.myrhbackend.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class JobOfferServiceImp implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    @Override
    public void create(JobOffer jobOffer) {
        jobOfferRepository.save(jobOffer);
    }
}
