package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.response.PlanResponse;
import com.example.Api_Postgresql.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/plans")
public class PlanController {

    private final PlanService planService;

    @GetMapping("/list-plans")
    public ResponseEntity<List<PlanResponse>> listPlans() {
        return ResponseEntity.status(200).body(planService.getAllPlanFunctionalities());
    }

}
