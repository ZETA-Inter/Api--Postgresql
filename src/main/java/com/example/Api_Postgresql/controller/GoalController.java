package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.request.GoalRequestDTO;
import com.example.Api_Postgresql.dto.response.*;
import com.example.Api_Postgresql.service.GoalService;
import com.example.Api_Postgresql.swagger.GoalControllerDocs;
import com.example.Api_Postgresql.validation.OnCreate;
import com.example.Api_Postgresql.validation.OnPatch;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/goals")
public class GoalController implements GoalControllerDocs {

    private final GoalService goalService;

    @GetMapping("/list-goals-by-worker-id/{workerId}")
    public ResponseEntity<List<GoalWorkerResponse>> getGoalsByWorker(@PathVariable("workerId") Integer workerId) {
        return ResponseEntity.status(200).body(goalService.getGoalsByWorkerId(workerId));
    }

    @GetMapping("/list-goals-by-program-and-company")
    public ResponseEntity<List<GoalResponseDTO>> getGoalsByProgramAndCompany(@RequestParam("programId") Integer programId, @RequestParam("companyId") Integer companyId) {
        return ResponseEntity.status(200).body(goalService.getGoalsByProgramIdAndCompanyId(programId, companyId));
    }

    @GetMapping("/list-goals-by-company")
    public ResponseEntity<List<GoalResponseDTO>> getGoalsByCompany(@RequestParam("companyId") Integer companyId) {
        return ResponseEntity.status(200).body(goalService.getGoalsByCompanyId(companyId));
    }

    @PostMapping("/create")
    public ResponseEntity<GoalResponseDTO> createGoal(@RequestBody @Validated({OnCreate.class, Default.class}) GoalRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(goalService.createGoal(requestDTO));
    }

    @PatchMapping("/update/{goalId}")
    public ResponseEntity<String> updateGoal(@PathVariable Integer goalId,
                                                      @RequestBody @Validated({OnPatch.class}) GoalRequestDTO requestDTO) {
        goalService.partiallyUpdateGoal(goalId, requestDTO);
        return ResponseEntity.status(200).body("Company ID "+goalId+" partially updated sucessfully!");
    }

    @DeleteMapping("/delete/{goalId}")
    public ResponseEntity<String> deleteGoal(@PathVariable Integer goalId) {
        goalService.deleteGoal(goalId);
        return ResponseEntity.status(200).body("Goal with ID "+goalId+" deleted sucessfully!");
    }

    @GetMapping("/list-workers-goal-by-program-and-company")
    public ResponseEntity<List<WorkerProgramResponse>> getWorkersGoalByProgramAndCompany(@RequestParam("programId") Integer programId, @RequestParam("companyId") Integer companyId) {
        return ResponseEntity.status(200).body(goalService.getWorkersByProgramIdAndCompanyId(programId, companyId));
    }

    @GetMapping("/progress-goals/{workerId}")
    public ResponseEntity<GoalProgressResponseDTO> getGoalProgressPercentage(@PathVariable("workerId") Integer workerId) {
        return ResponseEntity.status(200).body(goalService.getGoalProgressPercentage(workerId));
    }

    @GetMapping("/segment-goal/{goalId}")
    public ResponseEntity<String> getSegmentGoal(@PathVariable("goalId") Integer goalId) {
        return ResponseEntity.status(200).body(goalService.getSegmentGoal(goalId));
    }

    @GetMapping("/finished-goals-percentage/{companyId}")
    public ResponseEntity<GoalProgressPercentage> getAverageFinishedGoals(@PathVariable("companyId") Integer companyId) {
        return ResponseEntity.status(200).body(goalService.getFinishedGoals(companyId));
    }

}