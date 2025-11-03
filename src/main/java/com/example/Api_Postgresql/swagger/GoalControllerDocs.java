package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.request.GoalRequestDTO;
import com.example.Api_Postgresql.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Goals",
        description = "Endpoints for managing goals, including creation, deletion, listing, partial updates, and progress tracking."
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
            description = "Creates a new goal and associates it with a company and optionally with a program."
    )
    ResponseEntity<GoalResponseDTO> createGoal(GoalRequestDTO requestDTO);

    @Operation(
            summary = "Partially update a goal",
            description = "Performs a partial update on a goal's data."
    )
    ResponseEntity<String> updateGoal(
            @Parameter(description = "Goal ID", example = "51") Integer goalId,
            GoalRequestDTO requestDTO);

    @Operation(
            summary = "Delete a goal by ID",
            description = "Deletes a goal by its ID if it exists."
    )
    ResponseEntity<String> deleteGoal(
            @Parameter(description = "Goal ID", example = "51") Integer goalId);

    @Operation(
            summary = "List workers and goals by program and company",
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

    @Operation(
            summary = "Get goal segment",
            description = "Retrieves the segment (category) of a specific goal."
    )
    ResponseEntity<String> getSegmentGoal(
            @Parameter(description = "Goal ID", example = "51") Integer goalId);

    @Operation(
            summary = "Get average finished goals",
            description = "Returns the percentage of finished goals for all workers in a company."
    )
    ResponseEntity<GoalProgressPercentage> getAverageFinishedGoals(
            @Parameter(description = "Company ID", example = "5") Integer companyId);

    @Operation(
            summary = "Count goals by program for a company",
            description = """
                Returns the number of goals grouped by each program of a specific company.
                Each item contains the program ID, program name, and total goal count.
                """
    )
    ResponseEntity<List<CountGoalProgramResponse>> countGoalsByProgramAndCompany(
            @Parameter(description = "Company ID", example = "5") Integer companyId);
}