package com.java.bom.entity.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "generalStatus",schema = "public")
@Getter
@Setter
public class GeneralStatusEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long statusId;
    private String shortCode;
    private String description;
    private String entityCodeName;

}
