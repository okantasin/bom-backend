package com.java.bom.entity;


import com.java.bom.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "model")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModelEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modelId;
    private double productionPercentage;
    private int stId;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @OneToMany(mappedBy = "model",cascade =CascadeType.ALL)
    private List<PartEntity> parts;
}
