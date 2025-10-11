package com.example.Api_Postgresql.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Builder
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
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @OneToMany(mappedBy = "workerProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("date DESC")
    @Builder.Default
    private List<Progress> progresses = new ArrayList<>();

    private Integer grade;

    public void addProgress(Progress progress) {
        progresses.add(progress);
        progress.setWorkerProgram(this);
    }

    public void removeProgress(Progress progress) {
        progresses.remove(progress);
        progress.setWorkerProgram(null);
    }

    public Progress getLatestProgress() {
        return progresses.isEmpty() ? null : progresses.getFirst();
    }

}
