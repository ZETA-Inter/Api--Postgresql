package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByEmail(String email);
}
