package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.WorkerGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkerGoalRepository extends JpaRepository<WorkerGoal, Integer> {

    List<WorkerGoal> findByGoalId(Integer goalId);

    Optional<WorkerGoal> findWorkerGoalByWorkerIdAndGoalId(Integer workerId, Integer goalId);

    List<WorkerGoal> findByWorkerId(Integer workerId);

    @Query("SELECT wg.worker.id FROM WorkerGoal wg WHERE wg.goal.id = :goalId")
    List<Integer> findWorkerIdsByGoalId(@Param("goalId") Integer goalId);

    Integer deleteByWorkerIdAndGoalId(Integer workerId, Integer goalId);

}
