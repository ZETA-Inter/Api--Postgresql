package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressRequestDTO {

    @NotNull(message = "field 'worker_id' is null", groups = OnCreate.class)
    @Min(value = 1, message = "worker_id' can't be less than 1")
    @JsonProperty("worker_id")
    private Integer workerId;

    @NotNull(message = "field 'program_id' is null", groups = OnCreate.class)
    @Min(value = 1, message = "program_id' can't be less than 1")
    @JsonProperty("program_id")
    private Integer programId;

    @NotNull(message = "field 'points' is null", groups = OnCreate.class)
    @Min(value = 0, message = "points' can't be less than 0")
    private Integer points;

    @NotNull(message = "field 'percentage' is null", groups = OnCreate.class)
    @Min(value = 0, message = "percentage' can't be less than 0")
    private Integer percentage;

}
