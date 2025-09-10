package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.model.Image;
import com.example.Api_Postgresql.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public Image createImage(String origin, String url, Integer referenceId) {
        Image image = Image.builder()
                .originTable(origin)
                .imageUrl(url)
                .sourceId(referenceId)
                .build();

        return imageRepository.save(image);
    };

}
