package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.dto.response.GoalWorkerResponse;
import com.example.Api_Postgresql.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Integer> {

    @Query(value = "SELECT * FROM fn_goals_by_worker(:workerId)", nativeQuery = true)
    List<GoalWorkerResponse> getGoalsByWorkerId(@Param("workerId") Integer workerId);

}
