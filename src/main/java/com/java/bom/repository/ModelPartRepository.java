package com.java.bom.repository;

import com.java.bom.entity.ModelPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ModelPartRepository extends JpaRepository<ModelPart, Long> {
    List<ModelPart> findByModelIdAndDeletedFalse(Long modelId);
}