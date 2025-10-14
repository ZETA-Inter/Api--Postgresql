package com.example.Api_Postgresql.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GoalProgressResponseDTO {

    private int count;
    private double progressPercentage;

}
