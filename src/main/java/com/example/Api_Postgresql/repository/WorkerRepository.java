package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.dto.response.WorkerProgressResponse;
import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    Worker findByEmail(String email);

    List<Worker> findAllByCompany_Id(Integer companyId);

    @Query(value = "SELECT * from fn_most_recent_progress(:workerId)", nativeQuery = true)
    WorkerProgressResponse getMostRecentProgress(Integer workerId);

    @Query(value = "SELECT * from fn_most_recent_progress(:workerId, :programId)", nativeQuery = true)
    WorkerProgressResponse getProgramProgress(Integer workerId, Integer programId);


}
