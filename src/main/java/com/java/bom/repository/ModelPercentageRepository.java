package com.java.bom.repository;

import com.java.bom.entity.ModelPercentage;
import com.java.bom.utils.PlanningType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ModelPercentageRepository extends JpaRepository<ModelPercentage, Long> {

    @Query("SELECT mp FROM ModelPercentage mp " +
            "JOIN mp.model m " +
            "JOIN m.project p " +
            "WHERE p.id = :projectId")
    List<ModelPercentage> findModelPercentageByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT mp FROM ModelPercentage mp " +
            "JOIN mp.model m " +
            "JOIN m.project p " +
            "JOIN GeneralStatusEntity gs ON gs.id = m.statusId " +
            "WHERE p.id = :projectId " +
            "AND p.planningType = :planningType " +
            "AND m.statusId=100 ")
    List<ModelPercentage> findByProjectIdAndPlanningTypeAndActive(
            @Param("projectId") Long projectId,
            @Param("planningType") PlanningType planningType,
            @Param("status") String status);

    @Query("SELECT mp FROM ModelPercentage mp " +
            "JOIN mp.model m " +
            "JOIN m.project p " +
            "JOIN GeneralStatusEntity gs ON gs.id = m.statusId " +
            "WHERE p.id = :projectId " +
            "AND p.planningType = :planningType " +
            "AND mp.year = :year " +
            "AND mp.week = :week " +
            "AND gs.shortCode = :status")
    List<ModelPercentage> findByProjectIdAndPlanningTypeAndYearAndWeek(
            @Param("projectId") Long projectId,
            @Param("planningType") PlanningType planningType,
            @Param("year") int year,
            @Param("week") Integer week,
            @Param("status") String status);

    @Query("SELECT mp FROM ModelPercentage mp " +
            "JOIN mp.model m " +
            "JOIN m.project p " +
            "JOIN GeneralStatusEntity gs ON gs.id = m.statusId " +
            "WHERE p.id = :projectId " +
            "AND p.planningType = :planningType " +
            "AND mp.year = :year " +
            "AND mp.month = :month " +
            "AND gs.shortCode = :status")
    List<ModelPercentage> findByProjectIdAndPlanningTypeAndYearAndMonth(
            @Param("projectId") Long projectId,
            @Param("planningType") PlanningType planningType,
            @Param("year") int year,
            @Param("month") Integer month,
            @Param("status") String status);

    ModelPercentage findModelPercentageByModel_Id(Long modelId);
}
