package com.example.Api_Postgresql.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponseDTO {

    @JsonProperty("origin_table")
    String originTable;
    @JsonProperty("image_url")
    String imageUrl;
    @JsonProperty("source_id")
    Integer sourceId;

}
