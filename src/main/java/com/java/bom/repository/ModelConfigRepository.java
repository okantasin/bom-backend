package com.java.bom.repository;


import com.java.bom.entity.ModelConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModelConfigRepository extends JpaRepository<ModelConfig, Long> {
    Optional<ModelConfig> findByModelNumber(String modelNumber);
}