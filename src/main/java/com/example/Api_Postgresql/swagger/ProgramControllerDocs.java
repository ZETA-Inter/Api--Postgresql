package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.request.ProgramRequestDTO;
import com.example.Api_Postgresql.dto.response.ProgramResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Programs",
        description = "Endpoints for managing programs, including creation, update, deletion, and listing."
)
public interface ProgramControllerDocs {

    @Operation(
            summary = "List all programs",
            description = "Retrieves a list of all programs."
    )
    ResponseEntity<List<ProgramResponseDTO>> listAllPrograms();

    @Operation(
            summary = "Find program by ID",
            description = "Retrieves details of a specific program by its ID."
    )
    ResponseEntity<ProgramResponseDTO> findProgramById(Integer id);

    @Operation(
            summary = "Create a new program",
            description = "Creates a new program with the provided information."
    )
    ResponseEntity<ProgramResponseDTO> createProgram(ProgramRequestDTO request);

    @Operation(
            summary = "Delete program by ID",
            description = "Deletes a program by its ID."
    )
    ResponseEntity<String> deleteProgram(Integer id);

    @Operation(
            summary = "Update program",
            description = "Updates all fields of an existing program."
    )
    ResponseEntity<String> updateProgram(Integer id, ProgramRequestDTO requestDTO);

    @Operation(
            summary = "Partially update program",
            description = "Performs a partial update on the program."
    )
    ResponseEntity<String> partiallyUpdateProgram(Integer id, ProgramRequestDTO requestDTO);
}
