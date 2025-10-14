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
public class PlanInfoRequestDTO {

    @NotNull(message = "field 'plan_info.id' is null", groups = OnCreate.class)
    @Min(value = 1, message = "plan_info.id' can't be less than 1")
    private Integer id;

    @NotNull(message = "field 'plan_info.frequency' is null", groups = OnCreate.class)
    private String frequency;

    @NotNull(message = "field 'plan_info.id' is null", groups = OnCreate.class)
    @Min(value = 0, message = "'plan_info.amount' can't be less than 1", groups = OnCreate.class)
    private Double amount;
}
