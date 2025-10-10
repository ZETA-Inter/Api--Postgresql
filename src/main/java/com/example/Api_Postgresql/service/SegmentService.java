package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.response.SegmentResponseDTO;
import com.example.Api_Postgresql.mapper.SegmentMapper;
import com.example.Api_Postgresql.model.Segment;
import com.example.Api_Postgresql.repository.SegmentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SegmentService {

    private final SegmentRepository segmentRepository;

    private final SegmentMapper segmentMapper;

    public List<SegmentResponseDTO> listSegments() {
        return segmentRepository.findAll()
                .stream()
                .map(segmentMapper::toSegmentResponseDTO)
                .toList();
    }

    public SegmentResponseDTO getSegmentById(Integer id) {
        return segmentRepository.findById(id)
                .map(segmentMapper::toSegmentResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Segment with id="+id+"not found"));
    }

    public Segment getSegmentByIdRaw(Integer id) {
        return segmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Segment with id="+id+"not found"));
    }

}
