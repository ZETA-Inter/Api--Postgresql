package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.dto.response.GoalWorkerResponse;
import com.example.Api_Postgresql.dto.response.WorkerProgramResponse;
import com.example.Api_Postgresql.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, Integer> {

    @Query(value = "SELECT * FROM fn_goals_by_worker(:workerId)", nativeQuery = true)
    List<GoalWorkerResponse> getGoalsByWorkerId(@Param("workerId") Integer workerId);

    @Query(value = "SELECT * FROM fn_workers_by_program(:companyId, :programId)", nativeQuery = true)
    List<WorkerProgramResponse> getWorkersGoalByProgramAndCompany(@Param("programId") Integer programId, @Param("companyId") Integer companyId);

    List<Goal> findByProgramIdAndCompanyId(Integer programId, Integer companyId);

    List<Goal> findByCompanyId(Integer companyId);

    Optional<Goal> findGoalByDescriptionAndProgram_IdAndCompanyId(String description, Integer programId, Integer companyId);

    @Procedure(procedureName = "public.sp_create_goal")
    void createGoal(
            @Param("pCompanyId") Integer companyId,
            @Param("pDescription") String description,
            @Param("pProgramId") Integer programId
    );

    Goal findGoalById(Integer id);
}
