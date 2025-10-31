package com.example.Api_Postgresql.dto.response;

public interface GoalWorkerResponse {
    Integer getGoalId();
    Integer getProgramId();
    String getGoalName();
    String getGoalDescription();
    boolean getCompleted();
}
