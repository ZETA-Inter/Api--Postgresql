package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.CompanyRequestDTO;
import com.example.Api_Postgresql.dto.CompanyResponseDTO;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Plan;
import com.example.Api_Postgresql.repository.PlanRepository;
import com.example.Api_Postgresql.repository.ResponsibleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ResponsibleRepository responsibleRepository;

    public Company convertCompanyRequestToCompany(CompanyRequestDTO request) {

        Plan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));

        Company company = new Company();
        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setPassword(request.getPassword());
        company.setPlan(plan);
        return company;
    }

    public CompanyResponseDTO convertCompanyToCompanyResponseDTO(Company company) {
        CompanyResponseDTO responseDTO = new CompanyResponseDTO();
        responseDTO.setId(company.getId());
        responseDTO.setName(company.getName());
        responseDTO.setEmail(company.getEmail());
        responseDTO.setPlanName(company.getPlan().getName());
        return responseDTO;
    }

}
