package com.java.bom.dto;

import java.util.List;

public class ModelPercentageRequest {
    private List<ModelPercentageData> modelPercentageList;

    // Getters & Setters
    public List<ModelPercentageData> getModelPercentageList() {
        return modelPercentageList;
    }

    public void setModelPercentageList(List<ModelPercentageData> modelPercentageList) {
        this.modelPercentageList = modelPercentageList;
    }
}