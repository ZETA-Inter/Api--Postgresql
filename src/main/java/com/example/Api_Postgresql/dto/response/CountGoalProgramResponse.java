package com.example.Api_Postgresql.dto.response;

public class CountGoalProgramResponse {

    private Integer programId;
    private String programName;
    private String segment;
    private Long countGoals;

    public CountGoalProgramResponse(Integer programId, String programName, String segment ,Long countGoals) {
        this.programId = programId;
        this.programName = programName;
        this.segment = segment;
        this.countGoals = countGoals;
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

    public Long getCountGoals() {
        return countGoals;
    }
}