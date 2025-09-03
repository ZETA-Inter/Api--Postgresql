package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.ProgramResponseDTO;
import com.example.Api_Postgresql.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/programs")
public class ProgramController {

    private final ProgramService service;

    @GetMapping("/listAll")
    public List<ProgramResponseDTO> listAllPrograms() {
        return service.listAllPrograms();
    }

    @GetMapping("/find-id/{id}")
    public ProgramResponseDTO findProgramById(@PathVariable Integer id) {
        return service.findProgramById(id);
    }

}
