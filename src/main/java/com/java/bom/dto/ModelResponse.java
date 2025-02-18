package com.java.bom.dto;

public class ModelResponse {

    private String modelName;
    private String modelNumber;
    private Double modelPercentages;
    private String status;
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public Double getModelPercentages() {
        return modelPercentages;
    }

    public void setModelPercentages(Double modelPercentages) {
        this.modelPercentages = modelPercentages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
