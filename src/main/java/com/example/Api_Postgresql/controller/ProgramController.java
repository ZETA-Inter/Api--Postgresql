package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.ProgramRequestDTO;
import com.example.Api_Postgresql.dto.ProgramResponseDTO;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/programs")
public class ProgramController {

    private final ProgramService service;

    @GetMapping("/listAll")
    public ResponseEntity<List<ProgramResponseDTO>> listAllPrograms() {
        return ResponseEntity.status(200).body(service.listAllPrograms());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<ProgramResponseDTO> findProgramById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(service.findProgramById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ProgramResponseDTO> createProgram(@RequestBody ProgramRequestDTO request) {
        return ResponseEntity.status(201).body(service.createProgram(request));
    }

}
