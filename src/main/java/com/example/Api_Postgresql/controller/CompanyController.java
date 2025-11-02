package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.request.CompanyRequestDTO;
import com.example.Api_Postgresql.dto.response.CompanyResponseDTO;
import com.example.Api_Postgresql.dto.response.CountWorkerProgramResponse;
import com.example.Api_Postgresql.dto.response.ProgramWorkerResponseDTO;
import com.example.Api_Postgresql.dto.response.WorkerRankingResponse;
import com.example.Api_Postgresql.service.CompanyService;
import com.example.Api_Postgresql.swagger.CompanyControllerDocs;
import com.example.Api_Postgresql.validation.OnCreate;
import com.example.Api_Postgresql.validation.OnPatch;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/companies")
public class CompanyController implements CompanyControllerDocs {

    private final CompanyService companyService;

    @GetMapping("/list")
    public ResponseEntity<List<CompanyResponseDTO>> list() {
        return ResponseEntity.status(200).body(companyService.list());
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<CompanyResponseDTO> findCompanyById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(companyService.findById(id));
    }

    @GetMapping("/find-email/{email}")
    public ResponseEntity<CompanyResponseDTO> findCompanyByEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(companyService.findByEmail(email));
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyResponseDTO> createCompany(@RequestBody @Validated({OnCreate.class, Default.class}) CompanyRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(companyService.createCompany(requestDTO));
    }

    @PatchMapping("/inactive/{id}")
    public ResponseEntity<String> inactiveCompany(@PathVariable Integer id) {
        companyService.inactiveCompany(id);
        return ResponseEntity.status(200).body("Company ID "+id+" sucessfully inactive!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Integer id, @Validated({OnCreate.class, Default.class}) @RequestBody CompanyRequestDTO requestDTO) {
        companyService.updateCompany(id, requestDTO);
        return ResponseEntity.status(201).body("Company ID "+id+" sucessfully updated!");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> partiallyUpdateCompany(@PathVariable Integer id, @RequestBody @Validated({OnPatch.class}) CompanyRequestDTO requestDTO) {
        companyService.partiallyUpdateCompany(id, requestDTO);
        return ResponseEntity.status(201).body("Company ID "+id+" partially updated sucessfully!");
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<WorkerRankingResponse>> getRanking(@RequestParam Integer companyId) {
        return ResponseEntity.status(200).body(companyService.getWorkersRanking(companyId));
    }

    @PostMapping("/assign-goal/{goalId}")
    public ResponseEntity<String> assignGoalToWorker(@RequestBody List<Integer> workerIds, @PathVariable("goalId") Integer goalId) {
        return ResponseEntity.status(200).body(companyService.assignGoal(workerIds, goalId));
    }

    @GetMapping("/average-progress-percentage/{companyId}")
    public ResponseEntity<Integer> getAverageProgressPercentage(@PathVariable Integer companyId) {
        return ResponseEntity.status(200).body(companyService.getAverageProgressPercentage(companyId));
    }

    @GetMapping("/average-points/{companyId}")
    public ResponseEntity<Integer> getAveragePoints(@PathVariable Integer companyId) {
        return ResponseEntity.status(200).body(companyService.getAveragePoints(companyId));
    }

    @GetMapping("/list-actual-worker-programs-by-id/{companyId}")
    public ResponseEntity<List<ProgramWorkerResponseDTO>> listActualWorkerPrograms(@PathVariable("companyId") Integer companyId) {
        return ResponseEntity.status(200).body(companyService.listActualWorkerPrograms(companyId));
    }

    @GetMapping("/count-workers-by-program/{companyId}")
    public ResponseEntity<List<CountWorkerProgramResponse>> countWorkersByProgram(@PathVariable Integer companyId) {
        return ResponseEntity.status(200).body(companyService.countWorkerProgramResponses(companyId));
    }

}
