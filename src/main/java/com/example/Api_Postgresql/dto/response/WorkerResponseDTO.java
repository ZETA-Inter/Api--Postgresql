package com.example.Api_Postgresql.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResponseDTO {

    private Integer id;

    private String name;

    private String email;

    @JsonProperty("image_url")
    private String imageUrl;

    private String planName;

    private String companyName;

}
