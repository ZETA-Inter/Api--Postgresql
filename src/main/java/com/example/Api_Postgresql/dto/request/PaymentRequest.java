package com.example.Api_Postgresql.dto.request;

import com.example.Api_Postgresql.validation.OnCreate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentRequest {

    @NotNull(message = "field 'userType' is null", groups = OnCreate.class)
    private String userType;

    @NotNull(message = "field 'userId' is null", groups = OnCreate.class)
    @Min(value = 0, message = "'UserId' can't be less than 1")
    private Integer userId;

    @NotNull(message = "field 'planoId' is null", groups = OnCreate.class)
    private PlanInfoRequestDTO planInfo;
}
