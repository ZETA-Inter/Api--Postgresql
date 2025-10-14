package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.PaymentRequest;
import com.example.Api_Postgresql.dto.response.PaymentResponse;
import com.example.Api_Postgresql.mapper.PaymentMapper;
import com.example.Api_Postgresql.model.Company;
import com.example.Api_Postgresql.model.Payment;
import com.example.Api_Postgresql.model.Plan;
import com.example.Api_Postgresql.model.Worker;
import com.example.Api_Postgresql.repository.CompanyRepository;
import com.example.Api_Postgresql.repository.PaymentRepository;
import com.example.Api_Postgresql.repository.PlanRepository;
import com.example.Api_Postgresql.repository.WorkerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    private final PlanRepository planRepository;

    private final CompanyRepository companyRepository;

    private final WorkerRepository workerRepository;


    public PaymentResponse createPayment(PaymentRequest paymentRequest) {

        Plan plan = planRepository.findById(paymentRequest.getPlanInfo().getId())
                .orElseThrow(() -> new EntityNotFoundException("Plan with id " + paymentRequest.getPlanInfo().getId() + " not found"));

        Payment payment;

        if (paymentRequest.getUserType().equals("worker")) {
            Worker worker = workerRepository.findById(paymentRequest.getUserId())
                    .orElseThrow(() ->  new EntityNotFoundException("Worker with id "+ paymentRequest.getUserId() +" not found"));

            payment = paymentMapper.toPayment(paymentRequest, plan, worker);
        } else {
            Company company = companyRepository.findById(paymentRequest.getUserId())
                    .orElseThrow(() ->  new EntityNotFoundException("Company with id "+ paymentRequest.getUserId() +" not found"));

            payment = paymentMapper.toPayment(paymentRequest, plan, company);
        }

        paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(payment);
    }

}
