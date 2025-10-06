package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.response.SegmentResponseDTO;
import com.example.Api_Postgresql.service.SegmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/segments")
@RestController
public class SegmentController {

    private final SegmentService segmentService;

    @GetMapping("/list_segments")
    public ResponseEntity<List<SegmentResponseDTO>> listSegments() {
        return ResponseEntity.status(200).body(segmentService.listSegments());
    }

}
