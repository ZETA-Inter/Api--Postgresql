package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsibleRequestDTO {

    @NotNull(message = "field 'firstName' is null", groups = OnCreate.class)
    private String firstName;

    @NotNull(message = "field 'lastName' is null", groups = OnCreate.class)
    private String lastName;

    @NotNull(message = "field 'email' is null", groups = OnCreate.class)
    private String email;

    @Min(value = 0, message = "'CompanyId' can't be less than 1")
    private Integer companyId;

}
