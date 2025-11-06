package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.WorkerRequestDTO;
import com.example.Api_Postgresql.dto.response.ImageResponseDTO;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Worker;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WorkerMapper {

    public Worker convertWorkerRequestToWorker(WorkerRequestDTO request, Company company) {
        Worker worker = new Worker();
        worker.setName(request.getName());
        worker.setEmail(request.getEmail());
        worker.setCompany(company);
        worker.setCreatedAt(LocalDateTime.now());
        worker.setActive(true);
        return worker;
    }

    public WorkerResponseDTO convertWorkerToWorkerResponse(Worker worker, ImageResponseDTO image) {
        WorkerResponseDTO responseDTO = new WorkerResponseDTO();
        responseDTO.setId(worker.getId());
        responseDTO.setName(worker.getName());
        responseDTO.setEmail(worker.getEmail());
        responseDTO.setCreatedAt(worker.getCreatedAt());
        responseDTO.setActive(worker.isActive());

        if (image != null) {
            responseDTO.setImageUrl(image.getImageUrl());
        }

        return responseDTO;
    }

}
