package com.example.Api_Postgresql.repository;

import com.example.Api_Postgresql.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findPaymentByWorker_IdOrderByPaidDate(Integer workerId);

}
