package com.example.Api_Postgresql.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDTO {

    private Integer id;

    private String name;

    private String email;

    private boolean active;

    @JsonProperty("plan_id")
    private Integer planId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

}
