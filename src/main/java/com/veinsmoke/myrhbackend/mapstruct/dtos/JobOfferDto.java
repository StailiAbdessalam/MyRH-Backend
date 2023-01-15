package com.veinsmoke.myrhbackend.mapstruct.dtos;

import com.veinsmoke.myrhbackend.enums.JobState;
import com.veinsmoke.myrhbackend.enums.JobType;
import com.veinsmoke.myrhbackend.enums.SalaryType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobOfferDto {

    @NotNull
    String profile;

    @NotNull
    String description;

    @NotNull
    JobType jobType;

    @NotNull
    String salary;

    @NotNull
    SalaryType salaryType;

    @NotNull
    String country;

    @NotNull
    JobState state;
}
