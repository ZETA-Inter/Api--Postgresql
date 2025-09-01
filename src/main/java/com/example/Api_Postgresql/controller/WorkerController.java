package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.*;
import com.example.Api_Postgresql.service.WorkerService;
import com.example.Api_Postgresql.validation.OnCreate;
import com.example.Api_Postgresql.validation.OnPatch;
import jakarta.validation.Valid;
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

    @GetMapping("/find-id/{id}")
    public ResponseEntity<WorkerResponseDTO> findCompanyById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(workerService.findById(id));
    }

    @GetMapping("/find-email/{email}")
    public ResponseEntity<WorkerResponseDTO> findCompanyByEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(workerService.findByEmail(email));
    }

    @PostMapping("/login")
    public ResponseEntity<CompanyResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        return ResponseEntity.status(200).body(workerService.login(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    @PostMapping("/create")
    public ResponseEntity<WorkerResponseDTO> createCompany(@RequestBody @Validated({OnCreate.class, Default.class}) WorkerRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(workerService.createWorker(requestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Integer id) {
        workerService.deleteWorker(id);
        return ResponseEntity.status(200).body("Worker ID "+id+" sucessfully deleted!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Integer id, @Validated({OnCreate.class, Default.class}) @RequestBody WorkerRequestDTO requestDTO) {
        workerService.updateWorker(id, requestDTO);
        return ResponseEntity.status(200).body("Worker ID "+id+" sucessfully updated!");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> partiallyUpdateCompany(@PathVariable Integer id, @RequestBody @Validated({OnPatch.class, Default.class}) WorkerRequestDTO requestDTO) {
        workerService.partiallyUpdateWorker(id, requestDTO);
        return ResponseEntity.status(200).body("Worker ID "+id+" partially updated sucessfully!");
    }

}
