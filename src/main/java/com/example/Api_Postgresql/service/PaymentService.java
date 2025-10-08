package com.example.Api_Postgresql.service;

import com.example.Api_Postgresql.dto.request.PaymentRequest;
import com.example.Api_Postgresql.dto.response.PaymentResponse;
import com.example.Api_Postgresql.mapper.PaymentMapper;
import com.example.Api_Postgresql.model.Payment;
import com.example.Api_Postgresql.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentMapper.toPayment(paymentRequest);
        paymentRepository.save(payment);
        return paymentMapper.toPaymentResponse(payment);
    }

}
