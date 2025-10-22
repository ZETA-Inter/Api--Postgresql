package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.service.WorkerGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/worker-goals")
public class WorkerGoalController {

    private final WorkerGoalService workerGoalService;

    @GetMapping("/list-workers-goal-by-goalId/{goalId}")
    public ResponseEntity<List<WorkerResponseDTO>> getWorkersByGoalId(@PathVariable("goalId") Integer goalId) {
        return ResponseEntity.status(200).body(workerGoalService.getWorkersByGoalId(goalId));
    }

    @GetMapping("/list-worker-ids-by-goalId/{goalId}")
    public ResponseEntity<List<Integer>> getWorkerIdsByGoalId(@PathVariable("goalId") Integer goalId) {
        return ResponseEntity.status(200).body(workerGoalService.getWorkerIdsByGoalId(goalId));
    }

    @DeleteMapping("/delete-workers-goal-by-goalId/{goalId}")
    public ResponseEntity<String> deleteWorkersByGoalId(@PathVariable("goalId") Integer goalId, @RequestBody List<Integer> workerIds) {
        return ResponseEntity.status(200).body(workerGoalService.deleteWorkersByGoalId(goalId, workerIds));
    }
}
