package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.WorkerRequestDTO;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Plan;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.model.Worker;
import com.example.Api_Postgresql.repository.CompanyRepository;
import com.example.Api_Postgresql.repository.PlanRepository;
import com.example.Api_Postgresql.repository.ProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper {

    @Autowired
    private CompanyRepository companyRepository;

    public Worker convertWorkerRequestToWorker(WorkerRequestDTO request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        Worker worker = new Worker();
        worker.setName(request.getName());
        worker.setEmail(request.getEmail());
        worker.setCpf(request.getCpf());
        worker.setBirthDate(request.getBirthDate());
        worker.setCompany(company);
        return worker;
    }

    public WorkerResponseDTO convertWorkerToWorkerResponse(Worker worker) {
        WorkerResponseDTO responseDTO = new WorkerResponseDTO();
        responseDTO.setId(worker.getId());
        responseDTO.setName(worker.getName());
        responseDTO.setEmail(worker.getEmail());
        responseDTO.setCpf(worker.getCpf());
        responseDTO.setCompanyName(worker.getCompany().getName());
        return responseDTO;
    }

}
