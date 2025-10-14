package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.CompanyRequestDTO;
import com.example.Api_Postgresql.dto.response.CompanyResponseDTO;
import com.example.Api_Postgresql.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company convertCompanyRequestToCompany(CompanyRequestDTO request) {
        Company company = new Company();
        company.setName(request.getName());
        company.setEmail(request.getEmail());
        return company;
    }

    public CompanyResponseDTO convertCompanyToCompanyResponseDTO(Company company) {
        CompanyResponseDTO responseDTO = new CompanyResponseDTO();
        responseDTO.setId(company.getId());
        responseDTO.setName(company.getName());
        responseDTO.setEmail(company.getEmail());
        return responseDTO;
    }

}
