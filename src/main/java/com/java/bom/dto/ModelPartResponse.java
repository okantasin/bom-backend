package com.java.bom.dto;

import java.util.List;

public class ModelPartResponse {
    private Long modelId;
    private String modelName;
    private List<PartResponse> parts;

    public ModelPartResponse(Long modelId, String modelName, List<PartResponse> parts) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.parts = parts;
    }

    // Getters & Setters
    public Long getModelId() { return modelId; }
    public void setModelId(Long modelId) { this.modelId = modelId; }

    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }

    public List<PartResponse> getParts() { return parts; }
    public void setParts(List<PartResponse> parts) { this.parts = parts; }
}