package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.request.ProgramRequestDTO;
import com.example.Api_Postgresql.dto.response.ProgramResponseDTO;
import com.example.Api_Postgresql.service.ProgramService;
import com.example.Api_Postgresql.swagger.ProgramControllerDocs;
import com.example.Api_Postgresql.validation.OnCreate;
import com.example.Api_Postgresql.validation.OnPatch;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/programs")
public class ProgramController implements ProgramControllerDocs {

    private final ProgramService service;

    @GetMapping("/list-all")
    public ResponseEntity<List<ProgramResponseDTO>> listAllPrograms() {
        return ResponseEntity.status(200).body(service.listAllPrograms());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<ProgramResponseDTO> findProgramById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(service.findProgramById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ProgramResponseDTO> createProgram(@RequestBody @Validated({OnCreate.class, Default.class}) ProgramRequestDTO request) {
        return ResponseEntity.status(201).body(service.createProgram(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProgram(@PathVariable Integer id) {
        service.deleteProgramById(id);
        return ResponseEntity.status(200).body("Program with ID "+id+" deleted successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProgram(@PathVariable Integer id, @Validated({OnCreate.class, Default.class}) @RequestBody ProgramRequestDTO requestDTO) {
        service.updateProgram(id, requestDTO);
        return ResponseEntity.ok().body("Program with ID "+id+" updated successfully!");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> partiallyUpdateProgram(@PathVariable Integer id, @Validated({OnPatch.class, Default.class}) @RequestBody ProgramRequestDTO requestDTO) {
        service.partiallyUpdateProgram(id, requestDTO);
        return ResponseEntity.ok().body("Program with ID "+id+" partially updated successfully!");
    }

}
