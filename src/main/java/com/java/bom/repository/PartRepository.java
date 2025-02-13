package com.java.bom.repository;

import com.java.bom.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long> {
    List<Part> findByModelId(Long modelId);
}