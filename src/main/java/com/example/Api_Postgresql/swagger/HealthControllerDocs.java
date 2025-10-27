package com.example.Api_Postgresql.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Health Check",
        description = "Endpoint used to verify if the API is running."
)
public interface HealthControllerDocs {

    @Operation(
            summary = "Check API health status",
            description = "Returns a simple 'OK' message indicating that the server is operational.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Server is up and running.",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(example = "OK")
                            )
                    )
            }
    )
    ResponseEntity<String> health();
}
