package com.java.bom.repository;

import com.java.bom.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PartRepository extends JpaRepository<Part, Long> {
}