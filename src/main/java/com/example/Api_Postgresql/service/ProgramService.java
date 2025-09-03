package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.ProgramResponseDTO;
import com.example.Api_Postgresql.mapper.ProgramMapper;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.repository.ProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    private final ProgramMapper mapper;

    public List<ProgramResponseDTO> listAllPrograms() {
        return programRepository.findAll()
                .stream()
                .map(p -> mapper.convertProgramToRequest(p))
                .toList();
    }

    public ProgramResponseDTO findProgramById(Integer id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        return mapper.convertProgramToRequest(program);
    }

}
