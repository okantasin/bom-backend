package com.java.bom.repository;

import com.java.bom.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByProjectIdAndDeletedFalse(Long projectId);

    List<Model> findByProjectId(Long projectId);
}