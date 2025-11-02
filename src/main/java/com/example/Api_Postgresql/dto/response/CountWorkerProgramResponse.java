package com.example.Api_Postgresql.dto.response;

public class CountWorkerProgramResponse {

    private Integer programId;
    private String programName;
    private Long countWorkers;

    public CountWorkerProgramResponse(Integer programId, String programName, Long countWorkers) {
        this.programId = programId;
        this.programName = programName;
        this.countWorkers = countWorkers;
    }

    public Integer getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }

    public Long getCountWorkers() {
        return countWorkers;
    }

}
