package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.response.ImageResponseDTO;
import com.example.Api_Postgresql.model.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

    public ImageResponseDTO convertToImageResponse(Image image){
        ImageResponseDTO responseDTO = new ImageResponseDTO();
        responseDTO.setOriginTable(image.getOriginTable());
        responseDTO.setImageUrl(image.getImageUrl());
        responseDTO.setSourceId(image.getSourceId());
        return responseDTO;
    }

}
