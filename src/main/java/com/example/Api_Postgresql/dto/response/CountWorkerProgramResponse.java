package com.example.Api_Postgresql.dto.response;

public class CountWorkerProgramResponse {

    private Integer programId;
    private String programName;
    private String segment;
    private Long countWorkers;

    public CountWorkerProgramResponse(Integer programId, String programName, String segment, Long countWorkers) {
        this.programId = programId;
        this.programName = programName;
        this.segment = segment;
        this.countWorkers = countWorkers;
    }

    public Integer getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }

    public String getSegment() {
        return segment;
    }

    public Long getCountWorkers() {
        return countWorkers;
    }

}
