package com.java.bom.entity.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "generalType",schema = "public")
@Getter
@Setter
public class GeneralTypeEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeId;
    private String shortCode;
    private String description;
    private String entityCodeName;
}
