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
@NoArgsConstructor
@AllArgsConstructor
public class ProgramRequestDTO {

    @NotNull(message = "field 'name' is null", groups = OnCreate.class)
    private String name;

    @NotNull(message = "field 'description' is null", groups = OnCreate.class)
    private String description;

    @Min(value = 0, message = "'QuantityModules' can't be less than 1")
    private Integer quantityModules;

    @NotNull(message = "field 'imageUrl' is null", groups = OnCreate.class)
    private String imageUrl;

    @Min(value = 0, message = "'SegmentId' can't be less than 1")
    private Integer segmentId;
}
