package com.java.bom.entity;

import com.java.bom.entity.common.BaseEntity;
import com.java.bom.utils.PlanningType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PlanningType planningType; // SABIT, AYLIK, HAFTALIK

    private PlanningType previousPlanningType; // Son kullanılan plan tipi

    private boolean deleted = false; // Soft delete için

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Model> models;

    // Constructors, Getters, and Setters
}

