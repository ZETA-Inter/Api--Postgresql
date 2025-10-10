package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.ProgramRequestDTO;
import com.example.Api_Postgresql.dto.response.ProgramResponseDTO;
import com.example.Api_Postgresql.model.Image;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.model.Segment;
import com.example.Api_Postgresql.repository.ImageRepository;
import com.example.Api_Postgresql.repository.ProgramRepository;
import com.example.Api_Postgresql.repository.SegmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProgramMapper {

    private final SegmentRepository segmentRepository;

    private final ImageRepository imageRepository;
    private final ProgramRepository programRepository;

    public Program convertRequestToProgram(ProgramRequestDTO request) {

        Segment segment = segmentRepository.findById(request.getSegmentId())
                .orElseThrow(() -> new EntityNotFoundException("Segment not found"));

        Program program = Program.builder()
                .name(request.getName())
                .description(request.getDescription())
                .segment(segment)
                .build();

        return program;
    }

    public ProgramResponseDTO convertProgramToResponse(Program program) {

        Segment segment = segmentRepository.findById(program.getSegment().getId())
                .orElseThrow(() -> new EntityNotFoundException("Segment not found"));

        Image image = imageRepository.findImageBySourceIdAndOriginTable(program.getId(), "programs");

        ProgramResponseDTO programResponseDTO = new ProgramResponseDTO();
        programResponseDTO.setId(program.getId());
        programResponseDTO.setName(program.getName());
        programResponseDTO.setDescription(program.getDescription());
        programResponseDTO.setSegmentName(segment.getName());

        if (image != null) {
            programResponseDTO.setImageUrl(image.getImageUrl());
        }

        return programResponseDTO;
    }

}
