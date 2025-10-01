package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
