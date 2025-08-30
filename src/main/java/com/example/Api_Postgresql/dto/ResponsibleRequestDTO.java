package com.example.Api_Postgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Integer companyId;

}
