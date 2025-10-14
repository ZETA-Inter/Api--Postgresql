package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.GoalRequestDTO;
import com.example.Api_Postgresql.dto.response.GoalProgressResponseDTO;
import com.example.Api_Postgresql.dto.response.GoalResponseDTO;
import com.example.Api_Postgresql.model.Goal;
import org.springframework.stereotype.Component;

@Component
public class GoalMapper {

    public Goal covertGoalRequestToGoalL(GoalRequestDTO request) {
        return new Goal();
    }

    public GoalResponseDTO convertGoalToGoalResponse(Goal goal) {
        return new GoalResponseDTO(
                goal.getId(),
                goal.getDescription()
        );
    }

    public GoalProgressResponseDTO convertToGoalProgressResponseDTO(int count, double progressPercentage) {
        return GoalProgressResponseDTO.builder()
                .count(count)
                .progressPercentage(progressPercentage)
                .build();
    }

}
