package com.example.Api_Postgresql.swagger;

import com.example.Api_Postgresql.dto.request.PaymentRequestDTO;
import com.example.Api_Postgresql.dto.response.PaymentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Payments",
        description = "Endpoints for managing payments, including creating new payment records."
)
public interface PaymentControllerDocs {

    @Operation(
            summary = "Create a new payment",
            description = "Creates a new payment record based on the provided payment data."
    )
    ResponseEntity<PaymentResponseDTO> createPayment(PaymentRequestDTO request);
}
