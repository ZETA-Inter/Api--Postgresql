package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.request.CompanyRequestDTO;
import com.example.Api_Postgresql.dto.response.CompanyResponseDTO;
import com.example.Api_Postgresql.dto.response.WorkerRankingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "Companies",
        description = "Endpoints for managing companies, including creation, updates, ranking, and goal assignment."
)
public interface CompanyControllerDocs {

    @Operation(summary = "List all companies", description = "Returns a list of all registered companies.")
    ResponseEntity<List<CompanyResponseDTO>> list();

    @Operation(summary = "Find company by ID", description = "Returns detailed information for a specific company.")
    ResponseEntity<CompanyResponseDTO> findCompanyById(
            @Parameter(description = "Company ID", example = "1") Integer id);

    @Operation(summary = "Find company by email", description = "Finds a company using its email address.")
    ResponseEntity<CompanyResponseDTO> findCompanyByEmail(
            @Parameter(description = "Company email", example = "contact@company.com") String email);

    @Operation(summary = "Create a new company", description = "Registers a new company in the system.")
    ResponseEntity<CompanyResponseDTO> createCompany(CompanyRequestDTO requestDTO);

    @Operation(summary = "Inactivate company", description = "Marks the company as inactive without deleting it.")
    ResponseEntity<String> inactiveCompany(
            @Parameter(description = "Company ID", example = "5") Integer id);

    @Operation(summary = "Update company", description = "Updates all data of a specific company.")
    ResponseEntity<String> updateCompany(
            @Parameter(description = "Company ID", example = "5") Integer id,
            CompanyRequestDTO requestDTO);

    @Operation(summary = "Partially update company", description = "Performs a partial update on company data.")
    ResponseEntity<String> partiallyUpdateCompany(
            @Parameter(description = "Company ID", example = "5") Integer id,
            CompanyRequestDTO requestDTO);

    @Operation(summary = "Get company ranking", description = "Returns the ranking of workers within a specific company.")
    ResponseEntity<List<WorkerRankingResponse>> getRanking(
            @Parameter(description = "Company ID", example = "5") Integer companyId);

    @Operation(summary = "Assign goal to workers", description = "Assigns a goal to a list of workers belonging to a company.")
    ResponseEntity<String> assignGoalToWorker(
            @Parameter(description = "List of worker IDs", example = "[1, 2, 3]") List<Integer> workerIds,
            @Parameter(description = "Goal ID", example = "10") Integer goalId);
}