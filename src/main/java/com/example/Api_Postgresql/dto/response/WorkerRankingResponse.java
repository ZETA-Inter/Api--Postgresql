package com.example.Api_Postgresql.dto.response;

public interface WorkerRankingResponse {
    Long getRankPosition();
    Integer getWorkerId();
    String getWorkerName();
    Long getTotalPoints();
    String getWorkerEmail();
}