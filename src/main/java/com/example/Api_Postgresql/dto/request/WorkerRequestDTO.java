package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
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

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("plan_info")
    private PlanInfoRequestDTO planInfo;

    @JsonProperty("company_id")
    @Min(value = 1, message = "field 'company_id' can't be less than 1")
    private Integer companyId;

}
