package com.example.Api_Postgresql.dto.response;

import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.model.Progress;
import com.example.Api_Postgresql.model.Worker;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class WorkerProgramResponseDTO {

    private Integer id;

    private Worker worker;

    private Program program;

    private List<Progress> progresses;

    private Integer grade;

}
