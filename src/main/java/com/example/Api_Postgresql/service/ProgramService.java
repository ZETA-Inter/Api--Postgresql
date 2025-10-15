package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.ProgramRequestDTO;
import com.example.Api_Postgresql.dto.response.ProgramResponseDTO;
import com.example.Api_Postgresql.dto.response.SegmentResponseDTO;
import com.example.Api_Postgresql.exception.EntityAlreadyExists;
import com.example.Api_Postgresql.mapper.ProgramMapper;
import com.example.Api_Postgresql.mapper.SegmentMapper;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.model.Segment;
import com.example.Api_Postgresql.repository.ProgramRepository;
import com.example.Api_Postgresql.repository.SegmentRepository;
import com.example.Api_Postgresql.validation.ProgramPatchValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    private final SegmentRepository segmentRepository;

    private final ProgramMapper mapper;

    private final SegmentMapper segmentMapper;

    private final ProgramPatchValidation validator;

    private final SegmentService segmentService;

    public List<ProgramResponseDTO> listAllPrograms() {
        return programRepository.findAll()
                .stream()
                .map(p -> mapper.convertProgramToResponse(p, segmentMapper.toSegmentResponseDTO(p.getSegment())))
                .toList();
    }

    public ProgramResponseDTO findProgramById(Integer id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        return mapper.convertProgramToResponse(program, segmentMapper.toSegmentResponseDTO(program.getSegment()));
    }

    public ProgramResponseDTO createProgram(ProgramRequestDTO request) {
        Program exists = programRepository.findByName(request.getName());
        if (exists != null) {
            throw new EntityAlreadyExists("Program name already exists");
        }

        Segment segment = segmentService.getSegmentByIdRaw(request.getSegmentId());

        Program program = mapper.convertRequestToProgram(request, segment);
        programRepository.save(program);

        SegmentResponseDTO segmentResponse = segmentMapper.toSegmentResponseDTO(segment);

        return mapper.convertProgramToResponse(program, segmentResponse);
    }

    public void deleteProgramById(Integer id) {
        programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        programRepository.deleteById(id);
    }

    public void updateProgram(Integer id, ProgramRequestDTO request) {
        Program exists = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        Segment segment = segmentService.getSegmentByIdRaw(request.getSegmentId());

        Program newProgram = mapper.convertRequestToProgram(request, segment);
        newProgram.setId(id);
        programRepository.save(newProgram);
    }

    public void partiallyUpdateProgram(Integer id, ProgramRequestDTO request) {
        Program exists = programRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        Segment segment = null;
        if (request.getSegmentId() != null) {
            segment = segmentRepository.findById(request.getSegmentId())
                    .orElseThrow(() -> new EntityNotFoundException("Segment not found"));
            request.setSegmentId(segment.getId());
        }

        Program newProgram = validator.validator(request, exists, segment);
        programRepository.save(newProgram);
    }

}
