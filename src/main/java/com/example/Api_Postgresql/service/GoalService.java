package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.GoalRequestDTO;
import com.example.Api_Postgresql.dto.response.GoalResponseDTO;
import com.example.Api_Postgresql.dto.response.GoalWorkerResponse;
import com.example.Api_Postgresql.dto.response.WorkerProgramResponse;
import com.example.Api_Postgresql.exception.EntityAlreadyExists;
import com.example.Api_Postgresql.mapper.GoalMapper;
import com.example.Api_Postgresql.model.Goal;
import com.example.Api_Postgresql.repository.GoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GoalService {

    private final GoalRepository goalRepository;

    private final GoalMapper goalMapper;

    public List<GoalWorkerResponse> getGoalsByWorkerId(int workerId){
        List<GoalWorkerResponse> goals = goalRepository.getGoalsByWorkerId(workerId);

        if (goals.isEmpty()) {
            throw new EntityNotFoundException("Goals not found");
        }

        return goals;
    }

    public List<GoalResponseDTO> getGoalsByProgramIdAndCompanyId(Integer programId, Integer companyId) {
        return goalRepository.findByProgramIdAndCompanyId(programId, companyId).stream()
                .map(goalMapper::convertGoalToGoalResponse)
                .toList();
    }

    public GoalResponseDTO createGoal(GoalRequestDTO request) {
        goalRepository.createGoal(request.getCompanyId(), request.getDescription(), request.getProgramId());
        Goal goal = goalRepository.findGoalByDescriptionAndProgram_Id(request.getDescription(), request.getProgramId())
                .orElseThrow(() -> new EntityNotFoundException("Create goal failed"));

        return goalMapper.convertGoalToGoalResponse(goal);
    }

    public List<WorkerProgramResponse> getWorkersByProgramIdAndCompanyId(Integer programId, Integer companyId) {
        List<WorkerProgramResponse> workers = goalRepository.getWorkersGoalByProgramAndCompany(programId, companyId);

        if  (workers.isEmpty()) {
            throw new EntityNotFoundException("Goals not found");
        }

        return workers;
    }

}
