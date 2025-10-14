package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.dto.response.PlanFunctionalitiesResponse;
import com.example.Api_Postgresql.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Integer> {

    @Query(value = "SELECT * FROM fn_functionalities_by_plan(:planId)", nativeQuery = true)
    List<PlanFunctionalitiesResponse> getFunctionalitiesByPlan(@Param("planId") Integer planId);

}
