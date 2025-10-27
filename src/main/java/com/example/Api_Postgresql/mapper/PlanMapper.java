package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.response.PlanResponseDTO;
import com.example.Api_Postgresql.model.Functionalities;
import com.example.Api_Postgresql.model.Plan;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@NoArgsConstructor
@Component
public class PlanMapper {

    public PlanResponseDTO createPlanFunctionalitiesResponse(Plan plan, List<Functionalities> functionalities) {
        return PlanResponseDTO.builder()
                .planId(plan.getId())
                .planName(plan.getName())
                .value(plan.getValue())
                .functionalities(functionalities)
                .build();

    }

}
