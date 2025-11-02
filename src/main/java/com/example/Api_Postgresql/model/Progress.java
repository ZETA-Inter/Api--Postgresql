package com.example.Api_Postgresql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    private LocalDateTime date;

    private Integer points;

    @Column(name = "progress_percentage")
    private Integer progressPercentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_program_id", nullable = false)
    private WorkerProgram workerProgram;

    public Progress(LocalDateTime date, Integer points, Integer progressPercentage) {
        this.date = date;
        this.points = points;
        this.progressPercentage = progressPercentage;
    }
}
