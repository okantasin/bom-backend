package com.java.bom.dto;

import com.java.bom.utils.PlanningType;

public class ModelPercentageData {
    private Long modelId;
    private Double percentage;
    private int year;
    private Integer month; // Aylık planlama için
    private Integer week;  // Haftalık planlama için
    private PlanningType planningType; // SABIT, AYLIK, HAFTALIK

    // Constructors
    public ModelPercentageData() {}

    public ModelPercentageData(Long modelId, Double percentage, int year, Integer month, Integer week, PlanningType planningType) {
        this.modelId = modelId;
        this.percentage = percentage;
        this.year = year;
        this.month = month;
        this.week = week;
        this.planningType = planningType;
    }

    // Getters & Setters
    public Long getModelId() { return modelId; }
    public void setModelId(Long modelId) { this.modelId = modelId; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public Integer getMonth() { return month; }
    public void setMonth(Integer month) { this.month = month; }

    public Integer getWeek() { return week; }
    public void setWeek(Integer week) { this.week = week; }

    public PlanningType getPlanningType() { return planningType; }
    public void setPlanningType(PlanningType planningType) { this.planningType = planningType; }
}