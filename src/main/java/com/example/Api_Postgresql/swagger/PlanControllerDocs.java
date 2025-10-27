package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.response.PlanResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Plans",
        description = "Endpoints for retrieving plans and their functionalities."
)
public interface PlanControllerDocs {

    @Operation(
            summary = "List all plans",
            description = "Retrieves a list of all available plans and their functionalities."
    )
    ResponseEntity<List<PlanResponseDTO>> listPlans();
}
