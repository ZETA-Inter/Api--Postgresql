package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.request.GradeRequestDTO;
import com.example.Api_Postgresql.dto.request.ProgressRequestDTO;
import com.example.Api_Postgresql.service.WorkerProgramService;
import com.example.Api_Postgresql.swagger.WorkerProgramControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/worker-programs")
@RequiredArgsConstructor
public class WorkerProgramController implements WorkerProgramControllerDocs {

    private final WorkerProgramService workerProgramService;

    @PatchMapping("/grade")
    public ResponseEntity<String> updateGrade(@RequestBody GradeRequestDTO request) {
        return ResponseEntity.status(200).body(workerProgramService.updateGrade(
                request.getWorkerId(),
                request.getProgramId(),
                request.getGrade()));
    }

    @PostMapping("/add-progress")
    public ResponseEntity<String> addProgress(@RequestBody ProgressRequestDTO request) {
        workerProgramService.addProgress(request);
        return ResponseEntity.ok().body("Progress created successfully");
    }


}
