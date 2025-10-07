package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRequestDTO {

    @NotNull(message = "field 'name' is null", groups = OnCreate.class)
    private String name;

    @NotNull(message = "field 'email' is null", groups = OnCreate.class)
    private String email;

    @NotNull(message = "field 'cpf' is null", groups = OnCreate.class)
    private String cpf;

    private LocalDate birthDate;

    private String imageUrl;

    @Min(value = 0, message = "'PlanId' can't be less than 1")
    private Integer planId;

    @Min(value = 0, message = "'CompanyId' can't be less than 1")
    private Integer companyId;

}
