package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.ProgramResponseDTO;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.model.Segment;
import com.example.Api_Postgresql.repository.SegmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProgramMapper {

    private final SegmentRepository segmentRepository;

    public ProgramResponseDTO convertProgramToRequest(Program program) {

        Segment segment = segmentRepository.findById(program.getSegment().getId())
                .orElseThrow(() -> new EntityNotFoundException("Segment not found"));

        ProgramResponseDTO programResponseDTO = new ProgramResponseDTO();
        programResponseDTO.setId(program.getId());
        programResponseDTO.setName(program.getName());
        programResponseDTO.setDescription(program.getDescription());
        programResponseDTO.setSegmentName(segment.getName());
        return programResponseDTO;
    }

}
