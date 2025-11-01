package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.WorkerProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkerProgramRepository extends JpaRepository<WorkerProgram, Integer> {

    List<WorkerProgram> findAllByWorker_Id(Integer workerId);

    WorkerProgram findByWorker_IdAndProgram_Id(Integer workerId, Integer programId);

}
