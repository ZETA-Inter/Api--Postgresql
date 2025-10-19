package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.repository.WorkerGoalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkerGoalService {

    private final WorkerGoalRepository workerGoalRepository;

    private final WorkerService workerService;

    public List<WorkerResponseDTO> getWorkersByGoalId(Integer goalId) {
        List<Integer> workerGoals = workerGoalRepository.findWorkerIdsByGoalId(goalId);

        if (workerGoals == null) {
            throw new EntityNotFoundException("Nenhum produtor encontrado para a meta com ID: " + goalId);
        }

        return workerGoals.stream()
                .map(workerService::findById)
                .toList();
    }

    public List<Integer> getWorkerIdsByGoalId(Integer goalId) {
        List<Integer> workerGoals = workerGoalRepository.findWorkerIdsByGoalId(goalId);

        if (workerGoals == null) {
            throw new EntityNotFoundException("Nenhum produtor encontrado para a meta com ID: " + goalId);
        }

        return workerGoals;
    }

}
