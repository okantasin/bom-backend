package com.java.bom.dto;

import java.util.List;

public class ModelPercentageRequest {
    private List<ModelPercentageData> modelPercentageList;
    private Long projectId;
    private int year;
    private Integer month; // Aylık planlama için
    private Integer week;  // Haftalık planlama için


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    // Getters & Setters
    public List<ModelPercentageData> getModelPercentageList() {
        return modelPercentageList;
    }

    public void setModelPercentageList(List<ModelPercentageData> modelPercentageList) {
        this.modelPercentageList = modelPercentageList;
    }
}