package com.java.bom.entity.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "general_status", schema = "public") // Tablo adını snake_case formatında düzenledik
@Getter
@Setter
public class GeneralStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Standard ID

    @Column(nullable = false, unique = true, length = 50)
    private String shortCode; // Enum olarak da kullanılabilir

    @Column(nullable = false, length = 255)
    private String description; // Açıklama

    private int isActv;

    public GeneralStatusEntity() {}

    public GeneralStatusEntity(String code, String description) {
        this.shortCode = code;
        this.description = description;
    }
}