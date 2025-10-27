package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.request.ResponsibleRequestDTO;
import com.example.Api_Postgresql.dto.response.ResponsibleResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Responsibles",
        description = "Endpoints for managing responsibles, including creation, update, deletion, and listing."
)
public interface ResponsibleControllerDocs {

    @Operation(
            summary = "List all responsibles",
            description = "Retrieves a list of all responsibles."
    )
    ResponseEntity<List<ResponsibleResponseDTO>> listAllResponsibles();

    @Operation(
            summary = "Find responsible by ID",
            description = "Retrieves a responsible's details by their ID."
    )
    ResponseEntity<ResponsibleResponseDTO> findResponsibleById(Integer id);

    @Operation(
            summary = "Find responsible by email",
            description = "Retrieves a responsible's details by their email."
    )
    ResponseEntity<ResponsibleResponseDTO> findResponsibleByEmail(String email);

    @Operation(
            summary = "Create a new responsible",
            description = "Creates a new responsible with the provided information."
    )
    ResponseEntity<ResponsibleResponseDTO> createResponsible(ResponsibleRequestDTO requestDTO);

    @Operation(
            summary = "Delete responsible by ID",
            description = "Deletes a responsible by their ID."
    )
    ResponseEntity<String> deleteResponsible(Integer id);

    @Operation(
            summary = "Update responsible",
            description = "Updates all fields of an existing responsible."
    )
    ResponseEntity<String> updateResponsible(Integer id, ResponsibleRequestDTO requestDTO);

    @Operation(
            summary = "Partially update responsible",
            description = "Performs a partial update on the responsible."
    )
    ResponseEntity<String> partiallyUpdateResponsible(Integer id, ResponsibleRequestDTO requestDTO);
}
