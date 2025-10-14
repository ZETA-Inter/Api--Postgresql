package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoalRequestDTO {

    @NotNull(message = "field 'description' is null", groups = OnCreate.class)
    private String description;

    @Min(value = 0, message = "'companyId' can't be less than 1", groups = OnCreate.class)
    private Integer companyId;

    @Min(value = 0, message = "'programId' can't be less than 1", groups = OnCreate.class)
    private Integer programId;

    @Size(min = 1, message = "A goal must have at least one worker assigned", groups = OnCreate.class)
    private List<Integer> workerIds;

}
