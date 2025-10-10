package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.ProgramRequestDTO;
import com.example.Api_Postgresql.dto.response.ProgramResponseDTO;
import com.example.Api_Postgresql.dto.response.SegmentResponseDTO;
import com.example.Api_Postgresql.model.Image;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.model.Segment;
import com.example.Api_Postgresql.repository.ImageRepository;
import com.example.Api_Postgresql.repository.ProgramRepository;
import com.example.Api_Postgresql.repository.SegmentRepository;
import com.example.Api_Postgresql.service.SegmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProgramMapper {

    private final SegmentService segmentService;

    private final ImageRepository imageRepository;
    private final ProgramRepository programRepository;

    public Program convertRequestToProgram(ProgramRequestDTO request) {

        Segment segment = segmentService.getSegmentByIdRaw(request.getSegmentId());

        Program program = Program.builder()
                .name(request.getName())
                .description(request.getDescription())
                .segment(segment)
                .build();

        return program;
    }

    public ProgramResponseDTO convertProgramToResponse(Program program) {

        SegmentResponseDTO segment = segmentService.getSegmentById(program.getSegment().getId());

        Image image = imageRepository.findImageBySourceIdAndOriginTable(program.getId(), "programs");

        ProgramResponseDTO programResponseDTO = new ProgramResponseDTO();
        programResponseDTO.setId(program.getId());
        programResponseDTO.setName(program.getName());
        programResponseDTO.setDescription(program.getDescription());
        programResponseDTO.setSegment(segment);

        if (image != null) {
            programResponseDTO.setImageUrl(image.getImageUrl());
        }

        return programResponseDTO;
    }

}
