package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.WorkerRequestDTO;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Worker;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper {

    public Worker convertWorkerRequestToWorker(WorkerRequestDTO request, Company company) {
        Worker worker = new Worker();
        worker.setName(request.getName());
        worker.setEmail(request.getEmail());
        worker.setCompany(company);
        return worker;
    }

    public WorkerResponseDTO convertWorkerToWorkerResponse(Worker worker) {
        WorkerResponseDTO responseDTO = new WorkerResponseDTO();
        responseDTO.setId(worker.getId());
        responseDTO.setName(worker.getName());
        responseDTO.setEmail(worker.getEmail());

        if (worker.getCompany() != null) {
            responseDTO.setCompanyName(worker.getCompany().getName());
        }

        return responseDTO;
    }

}
