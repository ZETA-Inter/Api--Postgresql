package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.ProgramRequestDTO;
import com.example.Api_Postgresql.dto.ProgramResponseDTO;
import com.example.Api_Postgresql.exception.EntityAlreadyExists;
import com.example.Api_Postgresql.mapper.ProgramMapper;
import com.example.Api_Postgresql.model.Image;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.repository.ProgramRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    private final ImageService imageService;

    private final ProgramMapper mapper;

    public List<ProgramResponseDTO> listAllPrograms() {
        return programRepository.findAll()
                .stream()
                .map(p -> mapper.convertProgramToResponse(p))
                .toList();
    }

    public ProgramResponseDTO findProgramById(Integer id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        return mapper.convertProgramToResponse(program);
    }

    public ProgramResponseDTO createProgram(ProgramRequestDTO request) {
        Program exists = programRepository.findByName(request.getName());
        if (exists != null) {
            throw new EntityAlreadyExists("Program name already exists");
        }
        Program program = mapper.convertRequestToProgram(request);
        programRepository.save(program);

        imageService.createImage("programs", request.getImageUrl(), program.getId());

        return mapper.convertProgramToResponse(program);
    }

}
