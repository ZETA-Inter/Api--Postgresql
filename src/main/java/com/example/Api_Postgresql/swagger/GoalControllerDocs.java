package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.request.GoalRequestDTO;
import com.example.Api_Postgresql.dto.response.GoalProgressResponseDTO;
import com.example.Api_Postgresql.dto.response.GoalResponseDTO;
import com.example.Api_Postgresql.dto.response.GoalWorkerResponse;
import com.example.Api_Postgresql.dto.response.WorkerProgramResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Goals",
        description = "Endpoints for managing goals, including creation, deletion, listing, and progress tracking."
)
public interface GoalControllerDocs {

    @Operation(
            summary = "List goals by worker ID",
            description = "Retrieves all goals assigned to a specific worker."
    )
    ResponseEntity<List<GoalWorkerResponse>> getGoalsByWorker(
            @Parameter(description = "Worker ID", example = "1") Integer workerId);

    @Operation(
            summary = "List goals by program and company",
            description = "Retrieves all goals linked to a specific program and company."
    )
    ResponseEntity<List<GoalResponseDTO>> getGoalsByProgramAndCompany(
            @Parameter(description = "Program ID", example = "10") Integer programId,
            @Parameter(description = "Company ID", example = "5") Integer companyId);

    @Operation(
            summary = "List goals by company",
            description = "Retrieves all goals created for a specific company."
    )
    ResponseEntity<List<GoalResponseDTO>> getGoalsByCompany(
            @Parameter(description = "Company ID", example = "5") Integer companyId);

    @Operation(
            summary = "Create a new goal",
            description = "Creates a new goal and associates it with a company and optionally a program."
    )
    ResponseEntity<GoalResponseDTO> createGoal(GoalRequestDTO requestDTO);

    @Operation(
            summary = "Delete goal by ID",
            description = "Deletes a goal by its ID if it exists."
    )
    ResponseEntity<String> deleteGaol(
            @Parameter(description = "Goal ID", example = "51") Integer goalId);

    @Operation(
            summary = "List workers and their goals by program and company",
            description = "Retrieves all workers and their corresponding goals for a specific program and company."
    )
    ResponseEntity<List<WorkerProgramResponse>> getWorkersGoalByProgramAndCompany(
            @Parameter(description = "Program ID", example = "10") Integer programId,
            @Parameter(description = "Company ID", example = "5") Integer companyId);

    @Operation(
            summary = "Get worker goal progress",
            description = "Calculates and returns the percentage of goals completed by a worker."
    )
    ResponseEntity<GoalProgressResponseDTO> getGoalProgressPercentage(
            @Parameter(description = "Worker ID", example = "1") Integer workerId);
}
