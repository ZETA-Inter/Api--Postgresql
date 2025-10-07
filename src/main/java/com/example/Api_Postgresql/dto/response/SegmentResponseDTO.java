package com.example.Api_Postgresql.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SegmentResponseDTO {

    private int id;
    private String name;

}
