package com.example.Api_Postgresql.dto.response;

import com.example.Api_Postgresql.model.Functionalities;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlanResponse {

    private Integer planId;
    private String planName;
    private Double value;
    private List<Functionalities> functionalities;
}
