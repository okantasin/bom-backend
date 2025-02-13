package com.java.bom.repository;

import com.java.bom.entity.ModelPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ModelPercentageRepository extends JpaRepository<ModelPercentage, Long> {
    List<ModelPercentage> findByModelIdAndYearAndMonth(Long modelId, int year, Integer month);
    List<ModelPercentage> findByModelIdAndYearAndWeek(Long modelId, int year, Integer week);
}