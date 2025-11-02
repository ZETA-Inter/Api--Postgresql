package com.example.Api_Postgresql.dto.response;

public interface GoalWorkerResponse {
    Integer getGoalId();
    Integer getProgram();
    String getGoalName();
    String getGoalDescription();
    boolean getCompleted();
}
