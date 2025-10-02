package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.response.GoalWorkerResponse;
import com.example.Api_Postgresql.service.GoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    @GetMapping("/list_goals_by_worker_id/{workerId}")
    public ResponseEntity<List<GoalWorkerResponse>> getGoalsByWorker(@Param("workerId") Integer workerId) {
        return ResponseEntity.status(200).body(goalService.getGoalsByWorkerId(workerId));
    }

}