package com.example.Api_Postgresql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "progresses")
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private Integer points;

    @Column(name = "progress_percentage") // Campo que faltava
    private Integer progressPercentage;

    @OneToOne(mappedBy = "progress", fetch = FetchType.LAZY)
    private WorkerProgram workerProgram;


}
