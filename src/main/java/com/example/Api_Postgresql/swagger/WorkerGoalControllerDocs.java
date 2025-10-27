package com.example.Api_Postgresql.swagger;

import io.swagger.v3.oas.annotations.Operation;
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
    ResponseEntity<List<Integer>> getWorkerIdsByGoalId(Integer goalId);

    @Operation(
            summary = "Delete workers from goal",
            description = "Deletes the association of specified workers from a given goal."
    )
    ResponseEntity<String> deleteWorkersByGoalId(Integer goalId, List<Integer> workerIds);
}
