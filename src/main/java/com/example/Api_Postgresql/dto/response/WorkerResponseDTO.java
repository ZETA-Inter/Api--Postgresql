package com.example.Api_Postgresql.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResponseDTO {

    private Integer id;

    private String name;

    private String email;

    private boolean active;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("plan_name")
    private String planName;

    @JsonProperty("company_name")
    private String companyName;

    private Set<String> segments;

}
