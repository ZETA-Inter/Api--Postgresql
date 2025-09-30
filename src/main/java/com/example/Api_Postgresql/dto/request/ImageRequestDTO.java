package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
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

    @NotNull(message = "field 'originTable' is null", groups = OnCreate.class)
    private String originTable;

    private Integer sourceId;

    @NotNull(message = "field 'imageUrl' is null", groups = OnCreate.class)
    private String imageUrl;

}
