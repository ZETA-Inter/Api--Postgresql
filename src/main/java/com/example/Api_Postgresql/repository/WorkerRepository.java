package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    Worker findByEmail(String email);

}
