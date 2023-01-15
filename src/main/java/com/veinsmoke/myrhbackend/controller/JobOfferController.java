package com.veinsmoke.myrhbackend.controller;


import com.veinsmoke.myrhbackend.entity.JobOffer;
import com.veinsmoke.myrhbackend.mapstruct.dtos.JobOfferDto;
import com.veinsmoke.myrhbackend.mapstruct.mappers.JobOfferMapper;
import com.veinsmoke.myrhbackend.service.joboffer.JobOfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobOffer")
@Slf4j
public class JobOfferController {

    private final JobOfferService jobOfferService;
    private final JobOfferMapper jobOfferMapper;

    @PostMapping("/add")
    public ResponseEntity<HashMap<String, String>> createJobOffer(@Valid @RequestBody JobOfferDto jobOfferDto) {
        log.info(" job offer dto {}", jobOfferDto);
        JobOffer jobOffer = jobOfferMapper.jobOfferDtoToJobOffer(jobOfferDto);
        log.info(" job offer {}", jobOffer);
        jobOfferService.create(jobOffer);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Job offer created");
        return ResponseEntity.ok(response);
    }
}
