package com.veinsmoke.myrhbackend.mapstruct.mappers;

import com.veinsmoke.myrhbackend.entity.JobOffer;
import com.veinsmoke.myrhbackend.mapstruct.dtos.JobOfferDto;
import org.mapstruct.Mapper;

@Mapper( componentModel = "spring" )
public interface JobOfferMapper {

        JobOffer jobOfferDtoToJobOffer(JobOfferDto jobOfferDto);

}
