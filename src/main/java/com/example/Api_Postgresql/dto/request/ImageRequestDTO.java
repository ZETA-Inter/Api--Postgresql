package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ImageRequestDTO {

    @JsonProperty("origin_table")
    @NotNull(message = "field 'originTable' is null", groups = OnCreate.class)
    private String originTable;

    @JsonProperty("source_id")
    @Min(value = 1, message = "'sourceId' can't be less than 1")
    private Integer sourceId;

    @JsonProperty("image_url")
    @NotNull(message = "field 'imageUrl' is null", groups = OnCreate.class)
    private String imageUrl;

}
