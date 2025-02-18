package com.java.bom.dto;

public class ModelProductionResult {
    private Long modelId;
    private String modelName;
    private int productionCount;

    public ModelProductionResult(Long modelId, String modelName, int productionCount) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.productionCount = productionCount;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getProductionCount() {
        return productionCount;
    }

    public void setProductionCount(int productionCount) {
        this.productionCount = productionCount;
    }
}
