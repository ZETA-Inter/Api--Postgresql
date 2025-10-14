package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.PaymentRequestDTO;
import com.example.Api_Postgresql.dto.response.PaymentResponse;
import com.example.Api_Postgresql.dto.response.PaymentResponse.UserInfo;
import com.example.Api_Postgresql.dto.response.PaymentResponse.PlanInfo;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Payment;
import com.example.Api_Postgresql.model.Plan;
import com.example.Api_Postgresql.model.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class PaymentMapper {

    public Payment toPayment(PaymentRequestDTO request, Plan plan, Worker worker) {
        LocalDate paidDate = LocalDate.now();

        return new Payment(
                worker,
                plan,
                request.getPlanInfo().getAmount(),
                paidDate,
                request.getPlanInfo().getFrequency()
        );
    }

    public Payment toPayment(PaymentRequestDTO request, Plan plan, Company company) {
        LocalDate paidDate = LocalDate.now();

        return new Payment(
                company,
                plan,
                request.getPlanInfo().getAmount(),
                paidDate,
                request.getPlanInfo().getFrequency()
        );
    }

    public PaymentResponse toPaymentResponse(Payment payment) {
        UserInfo userInfo = new UserInfo();
        if (payment.getCompany() != null) {
            userInfo.setId(payment.getCompany().getId());
            userInfo.setType("company");
            userInfo.setName(payment.getCompany().getName());
        } else {
            userInfo.setId(payment.getWorker().getId());
            userInfo.setType("worker");
            userInfo.setName(payment.getWorker().getName());
        }

        PlanInfo planInfo = new PlanInfo(
                payment.getPlan().getId(),
                payment.getPlan().getName(),
                payment.getAmount(),
                payment.getFrequency()
        );

         return new PaymentResponse(
                payment.getId(),
                userInfo,
                planInfo,
                payment.getPaidDate()
        );
    }

}
