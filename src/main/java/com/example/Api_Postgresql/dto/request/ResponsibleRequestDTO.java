package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("first_name")
    @NotNull(message = "field 'firstName' is null", groups = OnCreate.class)
    private String firstName;

    @JsonProperty("last_name")
    @NotNull(message = "field 'lastName' is null", groups = OnCreate.class)
    private String lastName;

    @NotNull(message = "field 'email' is null", groups = OnCreate.class)
    private String email;

    @JsonProperty("company_id")
    @NotNull(message = "field 'companyId' is null", groups = OnCreate.class)
    @Min(value = 0, message = "'CompanyId' can't be less than 1")
    private Integer companyId;

}
