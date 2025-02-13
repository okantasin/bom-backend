package com.java.bom.repository;

import com.java.bom.entity.PartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<PartEntity, Long> {
}
