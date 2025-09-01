package com.example.Api_Postgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResponseDTO {

    private Integer id;

    private String name;

    private String email;

    private String planName;

    private String companyName;

}
