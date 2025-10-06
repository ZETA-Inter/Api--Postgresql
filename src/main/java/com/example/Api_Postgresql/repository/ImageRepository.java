package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image findImageBySourceIdAndOriginTable(Integer sourceId, String originTable);

}
