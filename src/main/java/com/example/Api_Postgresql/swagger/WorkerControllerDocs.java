package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.request.WorkerRequestDTO;
import com.example.Api_Postgresql.dto.response.ProgramWorkerResponseDTO;
import com.example.Api_Postgresql.dto.response.WorkerProgressResponse;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Workers",
        description = "Endpoints for managing workers, including creation, updates, progress tracking, and program assignments."
)
public interface WorkerControllerDocs {

    @Operation(summary = "List all workers", description = "Returns a list of all registered workers.")
    ResponseEntity<List<WorkerResponseDTO>> list();

    @Operation(summary = "List workers by company ID", description = "Returns all workers associated with a given company.")
    ResponseEntity<List<WorkerResponseDTO>> listByCompanyId(
            @Parameter(description = "Company ID", example = "1") Integer id);

    @Operation(summary = "List active workers by company ID", description = "Returns only active workers for a given company.")
    ResponseEntity<List<WorkerResponseDTO>> listActiveWorkersByCompanyId(
            @Parameter(description = "Company ID", example = "1") Integer id);

    @Operation(summary = "Find worker by ID", description = "Returns detailed information of a specific worker.")
    ResponseEntity<WorkerResponseDTO> findWorkerById(
            @Parameter(description = "Worker ID", example = "10") Integer id);

    @Operation(summary = "Find worker by email", description = "Returns worker information using their email address.")
    ResponseEntity<WorkerResponseDTO> findWorkerByEmail(
            @Parameter(description = "Worker email", example = "john.doe@example.com") String email);

    @Operation(summary = "Create new worker", description = "Registers a new worker in the system.")
    ResponseEntity<WorkerResponseDTO> createWorker(WorkerRequestDTO requestDTO);

    @Operation(summary = "List current programs by worker ID", description = "Returns programs currently assigned to the worker.")
    ResponseEntity<List<ProgramWorkerResponseDTO>> listActualProgramsById(
            @Parameter(description = "Worker ID", example = "10") Integer id);

    @Operation(summary = "Inactivate worker", description = "Marks the worker as inactive without deleting it.")
    ResponseEntity<String> inactiveWorker(
            @Parameter(description = "Worker ID", example = "10") Integer id);

    @Operation(summary = "Update worker", description = "Updates all data of a specific worker.")
    ResponseEntity<String> updateWorker(
            @Parameter(description = "Worker ID", example = "10") Integer id,
            WorkerRequestDTO requestDTO);

    @Operation(summary = "Partially update worker", description = "Performs a partial update on a worker's data.")
    ResponseEntity<String> partiallyUpdateWorker(
            @Parameter(description = "Worker ID", example = "10") Integer id,
            WorkerRequestDTO requestDTO);

    @Operation(summary = "Get most recent program progress", description = "Returns the most recent program progress record of a worker.")
    ResponseEntity<WorkerProgressResponse> getRecentProgramProgress(
            @Parameter(description = "Worker ID", example = "10") Integer workerId);

    @Operation(summary = "Get overall goals progress", description = "Returns the average progress percentage across all goals of the worker.")
    ResponseEntity<Integer> getOverallGoalsProgress(
            @Parameter(description = "Worker ID", example = "10") Integer workerId);

    @Operation(summary = "Get overall programs progress", description = "Returns the average progress percentage across all programs of the worker.")
    ResponseEntity<Integer> getOverallProgramsProgress(
            @Parameter(description = "Worker ID", example = "10") Integer workerId);

    @Operation(summary = "Get specific program progress", description = "Returns progress details for a specific program assigned to the worker.")
    ResponseEntity<WorkerProgressResponse> getProgramProgress(
            @Parameter(description = "Worker ID", example = "10") Integer workerId,
            @Parameter(description = "Program ID", example = "5") Integer programId);

    @Operation(summary = "Assign program to worker", description = "Assigns a specific program to a worker.")
    ResponseEntity<String> assignProgramToWorker(
            @Parameter(description = "Worker ID", example = "10") Integer workerId,
            @Parameter(description = "Program ID", example = "5") Integer programId);
}