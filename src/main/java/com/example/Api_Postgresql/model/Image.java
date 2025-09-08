package com.example.Api_Postgresql.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "origin_table", nullable = false)
    private String originTable;

    @Column(name = "source_id", nullable = false)
    private Integer sourceId;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

}
