package com.java.bom.repository;

import com.java.bom.entity.Model;
import com.java.bom.utils.PlanningType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByProjectId(Long projectId);

    @Query("SELECT p.planningType FROM Project p , Model  m where m.project.id=p.id and p.id =:projectId ")
    Optional<PlanningType>  findProjectByModeId(@Param("modelId") Long modelId,@Param("projectId") Long projectId);


    Optional<Model> findModelById(Long id);
}