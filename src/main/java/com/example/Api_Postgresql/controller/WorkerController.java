package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.request.WorkerRequestDTO;
import com.example.Api_Postgresql.dto.response.ProgramWorkerResponseDTO;
import com.example.Api_Postgresql.dto.response.WorkerProgressResponse;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.service.WorkerService;
import com.example.Api_Postgresql.validation.OnCreate;
import com.example.Api_Postgresql.validation.OnPatch;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/workers")
@RestController
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping("/list")
    public ResponseEntity<List<WorkerResponseDTO>> listar() {
        return ResponseEntity.status(200).body(workerService.list());
    }

    @GetMapping("/list-by-companyId/{id}")
    public ResponseEntity<List<WorkerResponseDTO>> listarByCompanyId(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(workerService.listWorkersByCompanyId(id));
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<WorkerResponseDTO> findWorkerById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(workerService.findById(id));
    }

    @GetMapping("/find-email/{email}")
    public ResponseEntity<WorkerResponseDTO> findWorkerByEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(workerService.findByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<WorkerResponseDTO> createWorker(@RequestBody @Validated({OnCreate.class, Default.class}) WorkerRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(workerService.createWorker(requestDTO));
    }

    @GetMapping("/list-actual-programs-by-id/{id}")
    public ResponseEntity<List<ProgramWorkerResponseDTO>> listActualProgramsById(@PathVariable("id") Integer id) {
        return ResponseEntity.status(200).body(workerService.listActualProgramsById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteWorker(@PathVariable Integer id) {
        workerService.deleteWorker(id);
        return ResponseEntity.status(200).body("Worker ID "+id+" sucessfully deleted!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateWorker(@PathVariable Integer id, @Validated({OnCreate.class, Default.class}) @RequestBody WorkerRequestDTO requestDTO) {
        workerService.updateWorker(id, requestDTO);
        return ResponseEntity.status(200).body("Worker ID "+id+" sucessfully updated!");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> partiallyUpdateWorker(@PathVariable Integer id, @RequestBody @Validated({OnPatch.class, Default.class}) WorkerRequestDTO requestDTO) {
        workerService.partiallyUpdateWorker(id, requestDTO);
        return ResponseEntity.status(200).body("Worker ID "+id+" partially updated sucessfully!");
    }

    @GetMapping("/recent-progress/{id}")
    public ResponseEntity<WorkerProgressResponse> getRecentProgress(@PathVariable("id") Integer workerId) {
        return ResponseEntity.status(200).body(workerService.getMostRecentProgress(workerId));
    }

    @GetMapping("/program-progress/{id}")
    public ResponseEntity<WorkerProgressResponse> getProgramProgress(@PathVariable("id") Integer workerId, @RequestParam Integer programId) {
        return ResponseEntity.status(200).body(workerService.getProgramProgress(workerId, programId));
    }

    @PostMapping("/assign-program")
    public ResponseEntity<String> assignProgramToWorker(@RequestParam Integer workerId, @RequestParam Integer programId) {
        workerService.assignProgramToWorker(workerId, programId);
        return ResponseEntity.status(201).body("Program ID "+programId+" assigned to Worker ID "+workerId+" sucessfully!");
    }

}
