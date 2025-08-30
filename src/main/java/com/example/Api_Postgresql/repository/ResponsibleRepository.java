package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsibleRepository extends JpaRepository<Responsible, Integer> {

    Responsible findByEmail(String email);

}
