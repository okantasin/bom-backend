package com.java.bom.dto;

import java.util.List;

public class ModelPartProductionResult {
    private Long modelId;
    private String modelName;
    private int modelProductionCount;
    private List<PartProductionDetail> partDetails;

    public ModelPartProductionResult(Long modelId, String modelName, int modelProductionCount, List<PartProductionDetail> partDetails) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelProductionCount = modelProductionCount;
        this.partDetails = partDetails;
    }

    public ModelPartProductionResult(String modelName, int modelProductionCount, List<PartProductionDetail> partDetails) {
        this.modelName = modelName;
        this.modelProductionCount = modelProductionCount;
        this.partDetails = partDetails;
    }

    public Long getModelId() { return modelId; }
    public String getModelName() { return modelName; }
    public int getModelProductionCount() { return modelProductionCount; }
    public List<PartProductionDetail> getPartDetails() { return partDetails; }
}