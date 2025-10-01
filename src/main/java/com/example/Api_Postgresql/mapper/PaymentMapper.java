package com.example.Api_Postgresql.mapper;

import com.example.Api_Postgresql.dto.request.PaymentRequest;
import com.example.Api_Postgresql.dto.response.PaymentResponse;
import com.example.Api_Postgresql.dto.response.PaymentResponse.UserInfo;
import com.example.Api_Postgresql.dto.response.PaymentResponse.PlanInfo;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Payment;
import com.example.Api_Postgresql.model.Plan;
import com.example.Api_Postgresql.model.Worker;
import com.example.Api_Postgresql.repository.CompanyRepository;
import com.example.Api_Postgresql.repository.PlanRepository;
import com.example.Api_Postgresql.repository.WorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class PaymentMapper {

    private final PlanRepository planRepository;

    private final CompanyRepository companyRepository;

    private final WorkerRepository workerRepository;

    public Payment toPayment(PaymentRequest request) {

        Plan plan = planRepository.findById(request.getPlanId())
                .orElseThrow(() -> new EntityNotFoundException("Plan with id "+ request.getPlanId() +" not found"));

        LocalDate paidDate = LocalDate.now();

        if (request.getUserType().equals("worker")) {
            Worker worker = workerRepository.findById(request.getUserId())
                    .orElseThrow(() ->  new EntityNotFoundException("Worker with id "+ request.getUserId() +" not found"));

            return new Payment(
                    worker,
                    plan,
                    plan.getValue(),
                    paidDate,
                    request.getFrequency()
            );
        } else {
            Company company = companyRepository.findById(request.getUserId())
                    .orElseThrow(() ->  new EntityNotFoundException("Company with id "+ request.getUserId() +" not found"));

            return new Payment(
                    company,
                    plan,
                    plan.getValue(),
                    paidDate,
                    request.getFrequency()
            );
        }
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
                payment.getAmount()
        );

         return new PaymentResponse(
                payment.getId(),
                userInfo,
                planInfo,
                payment.getPaidDate(),
                payment.getFrequency()
        );
    }

}
