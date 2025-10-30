package com.example.Api_Postgresql.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    private BigDecimal amount;

    @Column(name = "paid_date")
    private LocalDate paidDate;

    private String frequency;

    public Payment(Worker worker, Plan plan, BigDecimal amount, LocalDate paidDate, String frequency) {
        this.worker = worker;
        this.plan = plan;
        this.amount = amount;
        this.paidDate = paidDate;
        this.frequency = frequency;
    }

    public Payment(Company company, Plan plan, BigDecimal amount, LocalDate paidDate, String frequency) {
        this.company = company;
        this.plan = plan;
        this.amount = amount;
        this.paidDate = paidDate;
        this.frequency = frequency;
    }

}
