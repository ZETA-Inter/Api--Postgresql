package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.response.WorkerProgramResponseDTO;
import com.example.Api_Postgresql.model.WorkerProgram;
import org.springframework.stereotype.Component;

@Component
public class WorkerProgramMapper {

    public WorkerProgramResponseDTO convertToWorkerProgramResponse(WorkerProgram wp) {
        return WorkerProgramResponseDTO.builder()
                .id(wp.getId())
                .worker(wp.getWorker())
                .program(wp.getProgram())
                .progresses(wp.getProgresses())
                .grade(wp.getGrade())
                .build();

    }

}
