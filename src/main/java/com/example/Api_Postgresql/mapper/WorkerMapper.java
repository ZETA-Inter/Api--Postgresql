package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.WorkerRequestDTO;
import com.example.Api_Postgresql.dto.response.ImageResponseDTO;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Worker;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WorkerMapper {

    public Worker convertWorkerRequestToWorker(WorkerRequestDTO request, Company company) {
        Worker worker = new Worker();
        worker.setName(request.getName());
        worker.setEmail(request.getEmail());
        worker.setCompany(company);
        worker.setCreatedAt(LocalDate.now());
        worker.setActive(true);
        return worker;
    }

    public WorkerResponseDTO convertWorkerToWorkerResponse(Worker worker, ImageResponseDTO image, String planName) {
        WorkerResponseDTO responseDTO = new WorkerResponseDTO();
        responseDTO.setId(worker.getId());
        responseDTO.setName(worker.getName());
        responseDTO.setEmail(worker.getEmail());
        responseDTO.setPlanName(planName);
        responseDTO.setCreatedAt(worker.getCreatedAt());
        responseDTO.setActive(worker.isActive());

        if (worker.getCompany() != null) {
            responseDTO.setCompanyName(worker.getCompany().getName());
        }

        if (image != null) {
            responseDTO.setImageUrl(image.getImageUrl());
        }

        if (worker.getWorkerPrograms() != null) {
            responseDTO.setSegments(
                    worker.getWorkerPrograms().stream()
                            .map(wp -> wp.getProgram().getSegment().getName())
                            .collect(java.util.stream.Collectors.toSet())
            );
        }

        return responseDTO;
    }

}
