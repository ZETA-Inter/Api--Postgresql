package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.request.CompanyRequestDTO;
import com.example.Api_Postgresql.dto.response.CompanyResponseDTO;
import com.example.Api_Postgresql.dto.response.ProgramWorkerResponseDTO;
import com.example.Api_Postgresql.dto.response.WorkerRankingResponse;
import com.example.Api_Postgresql.dto.response.CountWorkerProgramResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Companies",
        description = "Endpoints for managing companies, including creation, updates, rankings, goal assignment, and progress statistics."
)
public interface CompanyControllerDocs {

    @Operation(
            summary = "List all companies",
            description = "Retrieves a list of all registered companies."
    )
    ResponseEntity<List<CompanyResponseDTO>> list();

    @Operation(
            summary = "Find company by ID",
            description = "Retrieves detailed information for a specific company by its ID."
    )
    ResponseEntity<CompanyResponseDTO> findCompanyById(
            @Parameter(description = "Company ID", example = "1") Integer id);

    @Operation(
            summary = "Find company by email",
            description = "Retrieves company details using its registered email address."
    )
    ResponseEntity<CompanyResponseDTO> findCompanyByEmail(
            @Parameter(description = "Company email", example = "contact@company.com") String email);

    @Operation(
            summary = "Create a new company",
            description = "Registers a new company in the system."
    )
    ResponseEntity<CompanyResponseDTO> createCompany(CompanyRequestDTO requestDTO);

    @Operation(
            summary = "Inactivate company",
            description = "Marks the specified company as inactive without removing it from the database."
    )
    ResponseEntity<String> inactiveCompany(
            @Parameter(description = "Company ID", example = "5") Integer id);

    @Operation(
            summary = "Update company",
            description = "Fully updates a company's information."
    )
    ResponseEntity<String> updateCompany(
            @Parameter(description = "Company ID", example = "5") Integer id,
            CompanyRequestDTO requestDTO);

    @Operation(
            summary = "Partially update company",
            description = "Performs a partial update on specific company fields."
    )
    ResponseEntity<String> partiallyUpdateCompany(
            @Parameter(description = "Company ID", example = "5") Integer id,
            CompanyRequestDTO requestDTO);

    @Operation(
            summary = "Get company ranking",
            description = "Retrieves the ranking of all workers in a specific company, ordered by performance."
    )
    ResponseEntity<List<WorkerRankingResponse>> getRanking(
            @Parameter(description = "Company ID", example = "5") Integer companyId);

    @Operation(
            summary = "Assign goal to workers",
            description = "Assigns an existing goal to one or more workers of a company."
    )
    ResponseEntity<String> assignGoalToWorker(
            @Parameter(description = "List of worker IDs", example = "[1, 2, 3]") List<Integer> workerIds,
            @Parameter(description = "Goal ID", example = "10") Integer goalId);

    @Operation(
            summary = "Get average progress percentage",
            description = "Returns the average completion percentage across all workers of a company."
    )
    ResponseEntity<Integer> getAverageProgressPercentage(
            @Parameter(description = "Company ID", example = "5") Integer companyId);

    @Operation(
            summary = "Get average points",
            description = "Returns the average total points earned by all workers in the specified company."
    )
    ResponseEntity<Integer> getAveragePoints(
            @Parameter(description = "Company ID", example = "5") Integer companyId);

    @Operation(
            summary = "List current worker programs",
            description = "Retrieves the list of active programs associated with workers in a given company."
    )
    ResponseEntity<List<ProgramWorkerResponseDTO>> listActualWorkerPrograms(
            @Parameter(description = "Company ID", example = "5") Integer companyId);

    @Operation(
            summary = "Count workers by program",
            description = """
                Returns the number of workers grouped by each program for a given company.
                Each item contains the program ID, program name, and the total worker count.
                """
    )
    ResponseEntity<List<CountWorkerProgramResponse>> countWorkersByProgram(
            @Parameter(description = "Company ID", example = "5") Integer companyId);
}
