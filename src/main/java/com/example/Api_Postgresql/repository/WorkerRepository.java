package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.dto.response.WorkerResponseDTO;
import com.example.Api_Postgresql.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    Worker findByEmail(String email);

    List<Worker> findAllByCompany_Id(Integer companyId);
}
