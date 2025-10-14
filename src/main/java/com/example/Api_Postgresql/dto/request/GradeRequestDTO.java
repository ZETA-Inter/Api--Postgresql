package com.example.Api_Postgresql.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GradeRequestDTO {

    @Min(value = 1, message = "Field 'worker_id' can't be less than 1")
    @JsonProperty("worker_id")
    private Integer workerId;

    @Min(value = 1, message = "Field 'program_id' can't be less than 1")
    @JsonProperty("program_id")
    private Integer programId;

    @Min(value = 0, message = "Field 'grade' can't be less than 0")
    private Integer grade;
}
