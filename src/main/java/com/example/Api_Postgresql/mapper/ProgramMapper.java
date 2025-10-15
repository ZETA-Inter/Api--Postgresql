package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.ProgramRequestDTO;
import com.example.Api_Postgresql.dto.response.ProgramResponseDTO;
import com.example.Api_Postgresql.dto.response.SegmentResponseDTO;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.model.Segment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProgramMapper {

    public Program convertRequestToProgram(ProgramRequestDTO request, Segment segment) {

        Program program = Program.builder()
                .name(request.getName())
                .description(request.getDescription())
                .segment(segment)
                .build();

        return program;
    }

    public ProgramResponseDTO convertProgramToResponse(Program program, SegmentResponseDTO segment) {

        ProgramResponseDTO programResponseDTO = new ProgramResponseDTO();
        programResponseDTO.setId(program.getId());
        programResponseDTO.setName(program.getName());
        programResponseDTO.setDescription(program.getDescription());
        programResponseDTO.setSegment(segment);

        return programResponseDTO;
    }

}
