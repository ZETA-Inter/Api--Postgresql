package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.response.PlanFunctionalitiesResponse;
import com.example.Api_Postgresql.model.Functionalities;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class FunctionalatitiesMapper {

    public Functionalities getFunctionalities(PlanFunctionalitiesResponse response) {
        return new Functionalities(response.getFunctionalityId(), response.getFunctionalityName());
    }

}
