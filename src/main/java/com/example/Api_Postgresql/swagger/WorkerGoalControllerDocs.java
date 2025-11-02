package com.example.Api_Postgresql.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Worker Goals",
        description = "Endpoints for managing workers associated with goals."
)
public interface WorkerGoalControllerDocs {

    @Operation(
            summary = "List worker IDs by goal ID",
            description = "Retrieves only the IDs of workers associated with a specific goal."
    )
    ResponseEntity<List<Integer>> getWorkerIdsByGoalId(
            @Parameter(description = "Goal ID", example = "51") Integer goalId);

    @Operation(
            summary = "Delete workers from goal",
            description = "Deletes the association of specified workers from a given goal."
    )
    ResponseEntity<String> deleteWorkersByGoalId(
            @Parameter(description = "Goal ID", example = "51") Integer goalId,
            @Parameter(description = "List of worker IDs to remove", example = "[1, 2, 3]") List<Integer> workerIds);

    @Operation(
            summary = "Mark goal as completed for worker",
            description = "Marks a specific goal as completed for a given worker."
    )
    ResponseEntity<String> completeGoal(
            @Parameter(description = "Worker ID", example = "10") Integer workerId,
            @Parameter(description = "Goal ID", example = "51") Integer goalId);
}