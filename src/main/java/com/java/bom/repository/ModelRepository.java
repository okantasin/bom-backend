package com.java.bom.repository;

import com.java.bom.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
}
