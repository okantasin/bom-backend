package com.java.bom.repository;

import com.java.bom.entity.ModelPercentage;
import com.java.bom.utils.PlanningType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelPercentageRepository extends JpaRepository<ModelPercentage, Long> {

    List<ModelPercentage> findByPlanningType(PlanningType planningType);
    List<ModelPercentage> findByPlanningTypeAndYearAndMonth(PlanningType planningType, int year, Integer month);
    List<ModelPercentage> findByPlanningTypeAndYearAndWeek(PlanningType planningType, int year, Integer week);


}