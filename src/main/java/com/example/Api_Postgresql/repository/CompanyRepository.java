package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.dto.response.ProgramWorkerResponseDTO;
import com.example.Api_Postgresql.dto.response.WorkerRankingResponse;
import com.example.Api_Postgresql.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByEmail(String email);

    @Query(value = "SELECT * FROM fn_get_workers_ranking(:companyId)", nativeQuery = true)
    List<WorkerRankingResponse> getWorkersRanking(@Param("companyId") Integer companyId);

    @Query(value = "SELECT * FROM fn_average_progress_percentage(:companyId)", nativeQuery = true)
    Integer getAverageProgressPercentage(@Param("companyId") Integer companyId);

    @Query(value = "SELECT * FROM fn_average_points_by_company(:companyId)", nativeQuery = true)
    Integer getAveragePoints(@Param("companyId") Integer companyId);

    @Query(value = "SELECT * FROM fn_programs_in_progress(:companyId)", nativeQuery = true)
    List<ProgramWorkerResponseDTO> listActualWorkerPrograms(@Param("companuId") Integer companyId);

}