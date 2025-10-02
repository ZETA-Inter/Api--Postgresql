package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.response.GoalWorkerResponse;
import com.example.Api_Postgresql.repository.GoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public List<GoalWorkerResponse> getGoalsByWorkerId(int workerId){
        List<GoalWorkerResponse> goals = goalRepository.getGoalsByWorkerId(workerId);

        if (goals.isEmpty()) {
            throw new EntityNotFoundException("Goals not found");
        }

        return goals;
    }

}
