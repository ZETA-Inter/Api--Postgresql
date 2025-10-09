package com.example.Api_Postgresql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "worker_programs")
public class WorkerProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false) // Mapeia para a coluna worker_id
    private Worker worker;

    // 3. Relação com a Entidade Program (Chave Estrangeira)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false) // Mapeia para a coluna program_id
    private Program program;

    @OneToOne(mappedBy = "workerProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private Progress progress;

    private Integer grade;


}
