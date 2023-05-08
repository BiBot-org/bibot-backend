package com.coderecipe.v1.rank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rank")
public class Rank {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
}
