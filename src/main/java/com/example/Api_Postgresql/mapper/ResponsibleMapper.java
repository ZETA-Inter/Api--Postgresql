package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.ResponsibleRequestDTO;
import com.example.Api_Postgresql.dto.response.ResponsibleResponseDTO;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Responsible;
import com.example.Api_Postgresql.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ResponsibleMapper {

    private final CompanyRepository companyRepository;

    public Responsible toResponsible(ResponsibleRequestDTO request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        Responsible responsible = new Responsible();
        responsible.setFirstName(request.getFirstName());
        responsible.setLastName(request.getLastName());
        responsible.setEmail(request.getEmail());
        responsible.setCompany(company);
        return responsible;
    }

    public ResponsibleResponseDTO toResponsibleResponseDTO(Responsible responsible) {
        ResponsibleResponseDTO response = new ResponsibleResponseDTO();
        response.setId(responsible.getId());
        response.setName(responsible.getFirstName() + " " + responsible.getLastName());
        response.setEmail(responsible.getEmail());
        response.setCompany_name(responsible.getCompany().getName());
        return response;
    }

}
