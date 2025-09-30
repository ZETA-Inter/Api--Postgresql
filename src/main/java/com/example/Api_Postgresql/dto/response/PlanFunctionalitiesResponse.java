package com.example.Api_Postgresql.dto.response;

import com.example.Api_Postgresql.model.Functionalities;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanFunctionalitiesResponse {

    private Integer planId;
    private String planName;
    private Double value;
    private Integer functionalityId;
    private String functionalityName;
}
