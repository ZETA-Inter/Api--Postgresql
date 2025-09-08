package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
