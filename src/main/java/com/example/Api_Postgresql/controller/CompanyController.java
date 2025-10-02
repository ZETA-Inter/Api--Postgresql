package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.request.CompanyRequestDTO;
import com.example.Api_Postgresql.dto.response.CompanyResponseDTO;
import com.example.Api_Postgresql.dto.request.LoginRequestDTO;
import com.example.Api_Postgresql.dto.response.WorkerRankingResponse;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.service.CompanyService;
import com.example.Api_Postgresql.validation.OnCreate;
import com.example.Api_Postgresql.validation.OnPatch;
import jakarta.validation.Valid;
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
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/list")
    public ResponseEntity<List<CompanyResponseDTO>> listar() {
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

    @PostMapping("/login")
    public ResponseEntity<CompanyResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        return ResponseEntity.status(200).body(companyService.login(loginRequest.getEmail(), loginRequest.getPassword()));
    }

    @PostMapping("/create")
    public ResponseEntity<CompanyResponseDTO> createCompany(@RequestBody @Validated({OnCreate.class, Default.class}) CompanyRequestDTO requestDTO) {
        return ResponseEntity.status(201).body(companyService.createCompany(requestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
        return ResponseEntity.status(200).body("Company ID "+id+" sucessfully deleted!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Integer id, @Validated({OnCreate.class, Default.class}) @RequestBody CompanyRequestDTO requestDTO) {
        companyService.updateCompany(id, requestDTO);
        return ResponseEntity.status(200).body("Company ID "+id+" sucessfully updated!");
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> partiallyUpdateCompany(@PathVariable Integer id, @RequestBody @Validated({OnPatch.class, Default.class}) CompanyRequestDTO requestDTO) {
        companyService.partiallyUpdateCompany(id, requestDTO);
        return ResponseEntity.status(200).body("Company ID "+id+" partially updated sucessfully!");
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<WorkerRankingResponse>> getRanking(@RequestParam Integer companyId) {
        return ResponseEntity.status(200).body(companyService.getWorkersRanking(companyId));
    }

}
