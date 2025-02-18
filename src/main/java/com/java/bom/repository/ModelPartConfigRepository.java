package com.java.bom.repository;


import com.java.bom.entity.ModelPartConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModelPartConfigRepository extends JpaRepository<ModelPartConfig, Long> {
    Optional<ModelPartConfig> findByModelIdAndPartId(Long modelId, Long partId);

    List<ModelPartConfig> findModelPartConfigByModelId(Long modelId);
}