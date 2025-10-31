package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.service.WorkerGoalService;
import com.example.Api_Postgresql.swagger.WorkerGoalControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/worker-goals")
public class WorkerGoalController implements WorkerGoalControllerDocs {

    private final WorkerGoalService workerGoalService;

    @GetMapping("/list-worker-ids-by-goalId/{goalId}")
    public ResponseEntity<List<Integer>> getWorkerIdsByGoalId(@PathVariable("goalId") Integer goalId) {
        return ResponseEntity.status(200).body(workerGoalService.getWorkerIdsByGoalId(goalId));
    }

    @DeleteMapping("/delete-workers-goal-by-goalId/{goalId}")
    public ResponseEntity<String> deleteWorkersByGoalId(@PathVariable("goalId") Integer goalId, @RequestBody List<Integer> workerIds) {
        return ResponseEntity.status(200).body(workerGoalService.deleteWorkersByGoalId(goalId, workerIds));
    }

    @PatchMapping("/complete-goal/{workerId}/{goalId}")
    public ResponseEntity<String> completeGoal(@PathVariable("workerId") Integer workerId, @PathVariable("goalId") Integer goalId) {
        return ResponseEntity.status(200).body(workerGoalService.completeGoal(workerId, goalId));
    }
}
