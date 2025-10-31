package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.model.WorkerGoal;
import com.example.Api_Postgresql.repository.WorkerGoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkerGoalService {

    private final WorkerGoalRepository workerGoalRepository;

    public List<Integer> getWorkerIdsByGoalId(Integer goalId) {
        List<Integer> workerGoals = workerGoalRepository.findWorkerIdsByGoalId(goalId);

        if (workerGoals == null) {
            throw new EntityNotFoundException("Workers not found with goal ID: " + goalId);
        }

        return workerGoals;
    }

    public List<WorkerGoal> listWorkerGoalsByWorkerId(Integer workerId) {
        List<WorkerGoal> workerGoals = workerGoalRepository.findByWorkerId(workerId);

        if (workerGoals.isEmpty()) {
            throw new EntityNotFoundException("Worker Goals not found for worker ID: " + workerId);
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

    public String completeGoal(Integer workerId, Integer goalId) {
        WorkerGoal wg = workerGoalRepository.findWorkerGoalByWorkerIdAndGoalId(workerId, goalId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find Worker Goal with goal ID "+goalId + " and worker ID "+workerId+"."));

        wg.setCompleted(true);
        workerGoalRepository.save(wg);

        return "Worker Goal completed with sucessfully for goal ID " + goalId + " and worker ID " + workerId;
    }

}
