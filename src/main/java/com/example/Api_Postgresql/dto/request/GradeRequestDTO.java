package com.example.Api_Postgresql.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GradeRequestDTO {

    @JsonProperty("worker_id")
    private Integer workerId;

    @JsonProperty("program_id")
    private Integer programId;

    private Integer grade;
}
