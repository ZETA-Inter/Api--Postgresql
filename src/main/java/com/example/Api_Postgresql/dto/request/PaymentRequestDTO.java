package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentRequestDTO {

    @JsonProperty("user_type")
    @NotNull(message = "field 'userType' is null", groups = OnCreate.class)
    private String userType;

    @JsonProperty("user_id")
    @NotNull(message = "field 'userId' is null", groups = OnCreate.class)
    @Min(value = 1, message = "'UserId' can't be less than 1")
    private Integer userId;

    @JsonProperty("plano_id")
    @NotNull(message = "field 'planoId' is null", groups = OnCreate.class)
    private PlanInfoRequestDTO planInfo;
}
