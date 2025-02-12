package com.java.bom.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class LogEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logId;
    private String entityName;
    private String actionType;
    private LocalDateTime logTime;


}
