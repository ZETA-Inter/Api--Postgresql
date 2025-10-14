package com.example.Api_Postgresql.dto.response;

import com.example.Api_Postgresql.model.Functionalities;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanResponse {

    @JsonProperty("plan_id")
    private Integer planId;
    @JsonProperty("plan_name")
    private String planName;
    private Double value;
    private List<Functionalities> functionalities;
}
