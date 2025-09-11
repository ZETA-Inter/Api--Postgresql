package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.WorkerRequestDTO;
import com.example.Api_Postgresql.dto.WorkerResponseDTO;
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
    private PlanRepository planRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ProgramRepository programRepository;

    public Worker convertWorkerRequestToWorker(WorkerRequestDTO request) {

        Plan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(() -> new EntityNotFoundException("Plan not found"));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        Program program = programRepository.findById(request.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Program not found"));

        Worker worker = new Worker();
        worker.setName(request.getName());
        worker.setEmail(request.getEmail());
        worker.setPassword(request.getPassword());
        worker.setBirthDate(request.getBirthDate());
        worker.setPlan(plan);
        worker.setCompany(company);
        worker.setProgram(program);
        return worker;
    }

    public WorkerResponseDTO convertWorkerToWorkerResponse(Worker worker) {
        WorkerResponseDTO responseDTO = new WorkerResponseDTO();
        responseDTO.setId(worker.getId());
        responseDTO.setName(worker.getName());
        responseDTO.setEmail(worker.getEmail());
        responseDTO.setPlanName(worker.getPlan().getName());
        responseDTO.setCompanyName(worker.getCompany().getName());
        return responseDTO;
    }

}
