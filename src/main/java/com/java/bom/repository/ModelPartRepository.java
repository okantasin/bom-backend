package com.java.bom.repository;

import com.java.bom.entity.ModelPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ModelPartRepository extends JpaRepository<ModelPart, Long> {
    List<ModelPart> findModelPartByModel_Id(Long modelId);


    @Query("Select  mp FROM  ModelPart mp where mp.part.id =: partId and mp.model.id =: modelId ")
    Optional<ModelPart> inquireModelPartByPart_IdAndModel_Id(@Param("partId") Long partId, @Param("modelId")Long modelId);
}