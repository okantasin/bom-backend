package com.java.bom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String partNumber;

    private boolean deleted = false; // Soft delete için

    public Part(Long partId) {
    }

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
}

