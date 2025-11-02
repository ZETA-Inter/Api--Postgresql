package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.GoalRequestDTO;
import com.example.Api_Postgresql.dto.response.*;
import com.example.Api_Postgresql.mapper.GoalMapper;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Goal;
import com.example.Api_Postgresql.model.Program;
import com.example.Api_Postgresql.repository.CompanyRepository;
import com.example.Api_Postgresql.repository.GoalRepository;
import com.example.Api_Postgresql.repository.ProgramRepository;
import com.example.Api_Postgresql.validation.GoalPatchValidation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GoalService {

    private final GoalRepository goalRepository;

    private final GoalMapper goalMapper;

    private final CompanyRepository companyRepository;

    private final GoalPatchValidation validation;

    private final ProgramRepository programRepository;

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
        goalRepository.createGoal(request.getCompanyId(), request.getDescription(), request.getName(), request.getProgramId());
        Goal goal = goalRepository.findGoalByDescriptionAndProgram_IdAndCompanyId(request.getDescription(), request.getProgramId(), request.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Create goal failed"));

        return goalMapper.convertGoalToGoalResponse(goal);
    }

    public void partiallyUpdateGoal(Integer id, GoalRequestDTO request) {
        Goal goal = goalRepository.findGoalById(id);
        if (goal != null) {

            Company company = goal.getCompany();
            if (request.getCompanyId() != null) {
                company = companyRepository.findById(request.getCompanyId())
                        .orElseThrow(() -> new EntityNotFoundException("Company with ID '"+request.getCompanyId()+"' don't exist!"));
            }

            Program program = goal.getProgram();
            if (request.getProgramId() != null) {
                program = programRepository.findById(request.getProgramId())
                        .orElseThrow(() -> new EntityNotFoundException("Program with ID '"+request.getProgramId()+"' don't exist!"));
            }

            Goal goalFinal = validation.validator(request, goal, company, program);

            goalRepository.save(goalFinal);
        } else {
            throw new EntityNotFoundException("Goal with ID '"+id+"' not found!");
        }
    }

    public void deleteGoal(Integer goalId) {
        Goal exists = goalRepository.findGoalById(goalId);

        if (exists == null) {
            throw new EntityNotFoundException("Goal with ID " + goalId + " not found");
        }

        goalRepository.deleteById(goalId);
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

    public String getSegmentGoal(Integer goalId) {
        return goalRepository.getSegmentGoal(goalId);
    }

    public GoalProgressPercentage getFinishedGoalsPercentage(Integer companyId) {
        return goalRepository.getFinishedGoalsPercentage(companyId);
    }

}
