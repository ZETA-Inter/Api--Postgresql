package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.response.ProgramResponseDTO;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.model.*;
import com.example.Api_Postgresql.repository.WorkerProgramRepository;
import com.example.Api_Postgresql.repository.WorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void assignProgramToWorker(Worker worker, Program program) {
        WorkerProgram exists = workerProgramRepository.findByWorker_IdAndProgram_Id(worker.getId(), program.getId());

        if (exists != null) {
            throw new IllegalArgumentException("Program with id=" + program.getId() + " is already assigned to worker with id=" + worker.getId());
        }



        WorkerProgram wp = WorkerProgram.builder()
                .worker(worker)
                .program(program)
                .grade(0)
                .build();

        Progress progress = new Progress();
        progress.setDate(LocalDate.now());
        progress.setPoints(0);
        progress.setProgressPercentage(0);

        // definir o vínculo bidirecional
        wp.setProgress(progress);
        progress.setWorkerProgram(wp);

        workerProgramRepository.save(wp);
    }

}
