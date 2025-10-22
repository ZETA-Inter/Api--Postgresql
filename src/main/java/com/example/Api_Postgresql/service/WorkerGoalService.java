package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.repository.WorkerGoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkerGoalService {

    private final WorkerGoalRepository workerGoalRepository;

    private final WorkerService workerService;

    public List<WorkerResponseDTO> getWorkersByGoalId(Integer goalId) {
        List<Integer> workerGoals = workerGoalRepository.findWorkerIdsByGoalId(goalId);

        if (workerGoals == null) {
            throw new EntityNotFoundException("Workers not found with goal ID: " + goalId);
        }

        return workerGoals.stream()
                .map(workerService::findById)
                .toList();
    }

    public List<Integer> getWorkerIdsByGoalId(Integer goalId) {
        List<Integer> workerGoals = workerGoalRepository.findWorkerIdsByGoalId(goalId);

        if (workerGoals == null) {
            throw new EntityNotFoundException("Workers not found with goal ID: " + goalId);
        }

        return workerGoals;
    }

    @Transactional
    public String deleteWorkersByGoalId(Integer goalId, List<Integer> workerIds) {
        for (Integer workerId : workerIds) {
            Integer deletedCount = workerGoalRepository.deleteByWorkerIdAndGoalId(workerId, goalId);
            if (deletedCount == 0) {
                throw new EntityNotFoundException("Can't find Worker Goal with goal ID "+goalId+" and worker ID "+workerId+".");
            }
        }
        return "Worker Goals deleted successfully for goal ID " + goalId + " and worker Ids " + workerIds;
    }

}
