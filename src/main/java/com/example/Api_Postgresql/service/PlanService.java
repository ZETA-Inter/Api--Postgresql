package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.response.PlanResponse;
import com.example.Api_Postgresql.mapper.FunctionalatitiesMapper;
import com.example.Api_Postgresql.mapper.PlanMapper;
import com.example.Api_Postgresql.model.Functionalities;
import com.example.Api_Postgresql.model.Plan;
import com.example.Api_Postgresql.repository.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlanService {

    private final PlanRepository planRepository;

    private final PlanMapper planMapper;

    private final FunctionalatitiesMapper functionalatitiesMapper;

    public List<PlanResponse> getAllPlanFunctionalities(){
        List<PlanResponse> planResponse = new ArrayList<>();
        List<Plan> listPlans = planRepository.findAll();

        if (listPlans.isEmpty()){
            throw new EntityNotFoundException("Planos não encontrados");
        }

        listPlans.forEach(plan -> {
            List<Functionalities> functionalities = planRepository.getFunctionalitiesByPlan(plan.getId()).stream()
                    .map(functionalatitiesMapper::getFunctionalities)
                    .toList();

            PlanResponse planFunctionalities = planMapper.createPlanFunctionalitiesResponse(plan, functionalities);
            planResponse.add(planFunctionalities);
        });
        return planResponse;
    }

}
