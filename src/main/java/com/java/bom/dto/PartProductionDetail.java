package com.java.bom.dto;

public class PartProductionDetail {
    private Long partId;
    private String partName;
    private int requiredPerModel;
    private int totalProductionCount;

    public PartProductionDetail(Long partId, String partName, int requiredPerModel, int totalProductionCount) {
        this.partId = partId;
        this.partName = partName;
        this.requiredPerModel = requiredPerModel;
        this.totalProductionCount = totalProductionCount;
    }

    public Long getPartId() { return partId; }
    public String getPartName() { return partName; }
    public int getRequiredPerModel() { return requiredPerModel; }
    public int getTotalProductionCount() { return totalProductionCount; }
}
