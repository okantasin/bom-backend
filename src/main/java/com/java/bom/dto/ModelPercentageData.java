package com.java.bom.dto;

import com.java.bom.utils.PlanningType;

public class ModelPercentageData {
    private String modelNumber;
    private Double percentage;


    // Constructors
    public ModelPercentageData() {}

    public ModelPercentageData(String modelNumber, Double percentage) {
        this.modelNumber = modelNumber;
        this.percentage = percentage;
    }

    // Getters & Setters
    public String getModelNumber() { return modelNumber; }
    public void getModelNumber(String modelId) { this.modelNumber = modelNumber; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }


}