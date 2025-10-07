package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.CompanyRequestDTO;
import com.example.Api_Postgresql.dto.response.CompanyResponseDTO;
import com.example.Api_Postgresql.dto.response.WorkerRankingResponse;
import com.example.Api_Postgresql.exception.BadCredentialsException;
import com.example.Api_Postgresql.exception.EntityAlreadyExists;
import com.example.Api_Postgresql.mapper.CompanyMapper;
import com.example.Api_Postgresql.mapper.WorkerMapper;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.repository.CompanyRepository;
import com.example.Api_Postgresql.validation.CompanyPatchValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    private final CompanyPatchValidation validation;

    private final ImageService imageService;

    private final DataSource dataSource;

    public List<CompanyResponseDTO> list() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyResponseDTO> companiesResponseDTO = new ArrayList<>();
        for (Company company : companies) {
            CompanyResponseDTO companyResponseDTO = companyMapper.convertCompanyToCompanyResponseDTO(company);
            companiesResponseDTO.add(companyResponseDTO);
        }
        return companiesResponseDTO;
    }

    public List<WorkerRankingResponse> getWorkersRanking(Integer companyId) {
        List<WorkerRankingResponse> workersRanking = companyRepository.getWorkersRanking(companyId);

        if (workersRanking == null) {
            throw new EntityNotFoundException("Workers not found");
        }

        return workersRanking;
    }

    public CompanyResponseDTO findById(Integer id) {
        Company exists = companyRepository.findById(id).get();
        if (exists == null) {
            throw new EntityNotFoundException("Company not found!");
        }
        return companyMapper.convertCompanyToCompanyResponseDTO(exists);
    }

    public CompanyResponseDTO findByEmail(String email) {
        Company exists = companyRepository.findByEmail(email);
        if (exists == null) {
            throw new EntityNotFoundException("Company not found!");
        }
        return companyMapper.convertCompanyToCompanyResponseDTO(exists);
    }

    public CompanyResponseDTO createCompany(CompanyRequestDTO request) {
        if (companyRepository.findByEmail(request.getEmail()) != null) {
            throw new EntityAlreadyExists("Company already exist!");
        }

        Company company = companyMapper.convertCompanyRequestToCompany(request);
        companyRepository.save(company);

        if (request.getImageUrl() != null) {
            imageService.createImage("companies", request.getImageUrl(), company.getId());
        }



        return companyMapper.convertCompanyToCompanyResponseDTO(company);
    }

    public void deleteCompany(Integer id) {
        Company company = companyRepository.findById(id).get();
        if (company == null) {
            throw new EntityNotFoundException("Company with ID '"+id+"' don't exist!");
        }
        companyRepository.delete(company);
    }

    public void updateCompany(Integer id, CompanyRequestDTO request) {
        Company companyExists = companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company with ID \"" + id + "\" not found"));
        Company company = companyMapper.convertCompanyRequestToCompany(request);
        company.setId(id);
        companyRepository.save(company);
    }

    public void partiallyUpdateCompany(Integer id, CompanyRequestDTO request) {
        Optional<Company> companyExists = companyRepository.findById(id);
        if (companyExists.isPresent()) {
            Company company = companyExists.get();

            Company companyFinal = validation.validator(request, company);

            companyRepository.save(companyFinal);
        } else {
            throw new EntityNotFoundException("Company with ID '"+id+"' not found!");
        }
    }

    public String assignGoal(List<Integer> workerIds, Integer goalId) {
        if (workerIds == null || workerIds.isEmpty()) {
            throw new IllegalArgumentException("Lista de workers não pode estar vazia");
        }

        String idsArray = workerIds.toString().replace("[", "{").replace("]", "}");
        String sql = String.format("CALL sp_assign_goal(%d, '%s')", goalId, idsArray);

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atribuir goal aos workers: " + e.getMessage(), e);
        }

        return "Workers successfully assigned";
    }

}
