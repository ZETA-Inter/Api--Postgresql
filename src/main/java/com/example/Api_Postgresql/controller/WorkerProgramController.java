package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.request.GradeRequestDTO;
import com.example.Api_Postgresql.service.WorkerProgramService;
import com.example.Api_Postgresql.swagger.WorkerProgramControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
