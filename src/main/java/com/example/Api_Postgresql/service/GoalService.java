package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.GoalRequestDTO;
import com.example.Api_Postgresql.dto.response.GoalProgressResponseDTO;
import com.example.Api_Postgresql.dto.response.GoalResponseDTO;
import com.example.Api_Postgresql.dto.response.GoalWorkerResponse;
import com.example.Api_Postgresql.dto.response.WorkerProgramResponse;
import com.example.Api_Postgresql.mapper.GoalMapper;
import com.example.Api_Postgresql.model.Goal;
import com.example.Api_Postgresql.repository.GoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<GoalResponseDTO> getGoalsByCompanyId(Integer companyId) {
        return goalRepository.findByCompanyId(companyId).stream()
                .map(goalMapper::convertGoalToGoalResponse)
                .toList();
    }

    public GoalResponseDTO createGoal(GoalRequestDTO request) {
        goalRepository.createGoal(request.getCompanyId(), request.getDescription(), request.getProgramId());
        Goal goal = goalRepository.findGoalByDescriptionAndProgram_IdAndCompanyId(request.getDescription(), request.getProgramId(), request.getCompanyId())
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

    public GoalProgressResponseDTO getGoalProgressPercentage(Integer workerId) {
        List<GoalWorkerResponse> goals = goalRepository.getGoalsByWorkerId(workerId);

        if (goals.isEmpty()) {
            throw new EntityNotFoundException("Goals not found");
        }

        int size = goals.size();
        int completedGoals = (int) goals.stream().filter(GoalWorkerResponse::getCompleted).count();
        double progress = (double) completedGoals / size * 100;

        return goalMapper.convertToGoalProgressResponseDTO(size, progress);
    }

}
