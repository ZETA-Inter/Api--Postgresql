package com.example.Api_Postgresql.controller;

import com.example.Api_Postgresql.dto.request.PaymentRequestDTO;
import com.example.Api_Postgresql.dto.response.PaymentResponseDTO;
import com.example.Api_Postgresql.service.PaymentService;
import com.example.Api_Postgresql.swagger.PaymentControllerDocs;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController implements PaymentControllerDocs {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentRequestDTO request) {
        return ResponseEntity.status(200).body(paymentService.createPayment(request));
    }

}
