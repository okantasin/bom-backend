package com.java.bom.entity;


import com.java.bom.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "part")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partId;

    private String partName;

    private int stId;

    @ManyToOne()
    @JoinColumn(name = "model_id", nullable = false)
    private ModelEntity model;
}
