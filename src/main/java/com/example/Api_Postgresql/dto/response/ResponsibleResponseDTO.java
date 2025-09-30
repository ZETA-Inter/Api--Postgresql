package com.example.Api_Postgresql.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private String company_name;
}
