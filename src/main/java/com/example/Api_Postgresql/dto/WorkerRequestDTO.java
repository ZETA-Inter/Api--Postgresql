package com.example.Api_Postgresql.dto;

import com.example.Api_Postgresql.validation.OnCreate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

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

    @NotNull(message = "field 'password' is null", groups = OnCreate.class)
    private String password;

    private LocalDate birthDate;

    @NotNull(message = "field 'imageUrl' is null", groups = OnCreate.class)
    private String imageUrl;

    @Min(value = 0, message = "'PlanId' can't be less than 1")
    private Integer planId;

    @Min(value = 0, message = "'CompanyId' can't be less than 1")
    private Integer companyId;

    @Min(value = 0, message = "'ProgramId' can't be less than 1")
    private Integer programId;



}
