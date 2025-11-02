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
public class ProgramWorkerResponseDTO extends ProgramResponseDTO {
    @JsonProperty("progress_percentage")
    private Integer progressPercentage;

    public ProgramWorkerResponseDTO(Integer id, String name, String description, Integer segmentId, String segmentName, String imageUrl, Integer progressPercentage) {
        super(id, name, description, imageUrl, new SegmentResponseDTO(segmentId, segmentName));
        this.progressPercentage = progressPercentage;
    }

}
