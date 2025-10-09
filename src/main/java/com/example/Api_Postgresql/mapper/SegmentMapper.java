package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.response.SegmentResponseDTO;
import com.example.Api_Postgresql.model.Segment;
import org.springframework.stereotype.Component;

@Component
public class SegmentMapper {

    public SegmentResponseDTO toSegmentResponseDTO(Segment segment) {
        return SegmentResponseDTO.builder()
                .id(segment.getId())
                .name(segment.getName())
                .build();
    }

}
