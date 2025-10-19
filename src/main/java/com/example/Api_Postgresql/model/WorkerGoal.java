package com.example.Api_Postgresql.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "worker_goals")
public class WorkerGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;

    private boolean completed;

}
