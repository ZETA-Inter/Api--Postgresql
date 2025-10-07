package com.example.Api_Postgresql.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentResponse {

    private Integer id;

    private UserInfo userInfo;

    private PlanInfo planInfo;

    private LocalDate paidDate;

    private String frequency;

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
    }

}
