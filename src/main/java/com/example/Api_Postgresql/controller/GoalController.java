package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.request.GoalRequestDTO;
import com.example.Api_Postgresql.dto.response.GoalProgressResponseDTO;
import com.example.Api_Postgresql.dto.response.GoalResponseDTO;
import com.example.Api_Postgresql.dto.response.GoalWorkerResponse;
import com.example.Api_Postgresql.dto.response.WorkerProgramResponse;
import com.example.Api_Postgresql.service.GoalService;
import com.example.Api_Postgresql.validation.OnCreate;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final GoalService goalService;

    @GetMapping("/list-goals-by-worker-id/{workerId}")
    public ResponseEntity<List<GoalWorkerResponse>> getGoalsByWorker(@PathVariable("workerId") Integer workerId) {
        return ResponseEntity.status(200).body(goalService.getGoalsByWorkerId(workerId));
    }

    @GetMapping("/list-goals-by-program-and-company")
    public ResponseEntity<List<GoalResponseDTO>> getGoalsByProgramAndCompany(@RequestParam("programId") Integer programId, @RequestParam("companyId") Integer companyId) {
        return ResponseEntity.status(200).body(goalService.getGoalsByProgramIdAndCompanyId(programId, companyId));
    }

    @PostMapping("/create")
    public ResponseEntity<GoalResponseDTO> createGoal(@RequestBody @Validated({OnCreate.class, Default.class}) GoalRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(goalService.createGoal(requestDTO));
    }

    @GetMapping("/list-workers-goal-by-program-and-company")
    public ResponseEntity<List<WorkerProgramResponse>> getWorkersGoalByProgramAndCompany(@RequestParam("programId") Integer programId, @RequestParam("companyId") Integer companyId) {
        return ResponseEntity.status(200).body(goalService.getWorkersByProgramIdAndCompanyId(programId, companyId));
    }

    @GetMapping("/progress-goals/{workerId}")
    public ResponseEntity<GoalProgressResponseDTO> getGoalProgressPercentage(@PathVariable("workerId") Integer workerId) {
        return ResponseEntity.status(200).body(goalService.getGoalProgressPercentage(workerId));
    }


}