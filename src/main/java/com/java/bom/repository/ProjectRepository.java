package com.java.bom.repository;

import com.java.bom.entity.ProjectEntity;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {

    Optional<ProjectEntity> findByName( @Param("name") String name);
}
