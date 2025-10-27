package com.example.Api_Postgresql.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentResponseDTO {

    private Integer id;

    @JsonProperty("user_info")
    private UserInfo userInfo;

    @JsonProperty("plan_info")
    private PlanInfo planInfo;

    @JsonProperty("paid_date")
    private LocalDate paidDate;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class UserInfo {
        private Integer id;
        private String type;
        private String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class PlanInfo {
        private Integer id;
        private String name;
        private Double amount;
        private String frequency;
    }

}
