package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Integer> {
}
