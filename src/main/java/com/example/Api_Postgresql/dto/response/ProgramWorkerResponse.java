package com.example.Api_Postgresql.dto.response;

public interface ProgramWorkerResponse {
    Integer getId();
    String getName();
    String getDescription();
    Integer getSegmentId();
    String getSegmentName();
    String getImageUrl();
    Double getProgressPercentage();
}
