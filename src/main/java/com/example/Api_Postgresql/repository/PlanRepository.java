package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
}
