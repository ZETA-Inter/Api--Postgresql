package com.example.Api_Postgresql.dto;

import com.example.Api_Postgresql.model.Segment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramRequestDTO {
    private String name;
    private String description;
    private Integer quantityModules;
    private String imageUrl;
    private Integer segmentId;
}
