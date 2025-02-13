package com.java.bom.entity;

import jakarta.persistence.*;

@Entity
public class ModelPercentage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int year;
    private Integer month;
    private Integer week;

    private double percentage;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    // Constructors, Getters, and Setters
}