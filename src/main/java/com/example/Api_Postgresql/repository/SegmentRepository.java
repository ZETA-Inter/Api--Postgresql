package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SegmentRepository extends JpaRepository<Segment, Integer> {
}
