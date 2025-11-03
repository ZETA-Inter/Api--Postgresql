package com.example.Api_Postgresql.dto.response;

public class CountGoalProgramResponse {

    private Integer programId;
    private String programName;
    private Long countGoals;

    public CountGoalProgramResponse(Integer programId, String programName, Long countGoals) {
        this.programId = programId;
        this.programName = programName;
        this.countGoals = countGoals;
    }

    public Integer getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }

    public Long getCountGoals() {
        return countGoals;
    }
}