package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoalRequestDTO {

    @NotNull(message = "field 'description' is null", groups = OnCreate.class)
    private String description;

    @JsonProperty("company_id")
    @NotNull(message = "field 'companyId' is null", groups = OnCreate.class)
    @Min(value = 1, message = "'companyId' can't be less than 1", groups = OnCreate.class)
    private Integer companyId;

    @JsonProperty("program_id")
    @Min(value = 1, message = "'programId' can't be less than 1", groups = OnCreate.class)
    private Integer programId;

}
