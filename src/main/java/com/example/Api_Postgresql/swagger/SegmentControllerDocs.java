package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.response.SegmentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Segments",
        description = "Endpoints for retrieving segments."
)
public interface SegmentControllerDocs {

    @Operation(
            summary = "List all segments",
            description = "Retrieves a list of all available segments."
    )
    ResponseEntity<List<SegmentResponseDTO>> listSegments();
}
