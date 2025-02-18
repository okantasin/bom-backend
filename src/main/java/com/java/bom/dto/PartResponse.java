package com.java.bom.dto;

public class PartResponse {
    private Long partId;
    private String partName;
    private int quantity;

    public PartResponse(Long partId, String partName, int quantity) {
        this.partId = partId;
        this.partName = partName;
        this.quantity = quantity;
    }

    // Getters & Setters
    public Long getPartId() { return partId; }
    public void setPartId(Long partId) { this.partId = partId; }

    public String getPartName() { return partName; }
    public void setPartName(String partName) { this.partName = partName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}