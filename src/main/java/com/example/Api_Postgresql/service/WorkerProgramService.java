package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.model.WorkerProgram;
import com.example.Api_Postgresql.repository.WorkerProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkerProgramService {

    private final WorkerProgramRepository workerProgramRepository;

    public List<WorkerProgram> listWorkerPrograms(Integer workerId) {
        List<WorkerProgram> wps = workerProgramRepository.findAllByWorker_Id(workerId);

        if (wps.isEmpty()) {
            throw new EntityNotFoundException("Worker program with worker_id="+ workerId +" not found");
        }

        return wps;
    }

}
