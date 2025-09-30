package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByEmail(String email);

    @Query(value = "SELECT fn_get_workers_ranking(:companyId)", nativeQuery = true)
    List<Worker> getWorkersRanking(@Param("companyId") Integer companyId);

}
