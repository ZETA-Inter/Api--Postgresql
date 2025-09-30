package com.example.Api_Postgresql.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponseDTO {

    private Integer id;
    private String name;
    private String description;
    private Integer quantityModules;
    private String segmentName;
}
