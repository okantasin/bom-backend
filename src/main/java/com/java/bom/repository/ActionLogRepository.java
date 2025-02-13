package com.java.bom.repository;

import com.java.bom.entity.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {
    List<ActionLog> findByEntityName(String entityName);
}