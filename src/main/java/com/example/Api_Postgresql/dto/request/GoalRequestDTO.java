package com.example.Api_Postgresql.dto.request;

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

    private String description;
    private Integer companyId;
    private Integer programId;
    private List<Integer> workerIds;

}
