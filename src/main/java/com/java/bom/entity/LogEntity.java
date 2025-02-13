package com.java.bom.entity;


import com.java.bom.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
@Getter
@Setter
public class LogEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logId;
    private String entityName;
    private String actionType;
    private LocalDateTime logTime;


}
