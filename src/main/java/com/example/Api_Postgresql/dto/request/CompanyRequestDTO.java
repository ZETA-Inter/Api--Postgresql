package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDTO {

    @NotNull(message = "field 'name' is null", groups = OnCreate.class)
    private String name;

    @NotNull(message = "field 'email' is null", groups = OnCreate.class)
    private String email;

    private String imageUrl;

    @JsonProperty("plan_info")
    @NotNull(message = "field 'plan_info' is null", groups = OnCreate.class)
    private PlanInfoRequestDTO planInfo;

}
