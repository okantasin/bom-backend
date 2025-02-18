package com.java.bom.dto;

public class PartDetail {
    private Long partId;
    private String partName;
    private int quantityRequired;
    private int quantityAvailable;
    private double percentageOfTotal; // Bu parça üretimde ne kadar önemli?

    public PartDetail(Long partId, String partName, int quantityRequired, int quantityAvailable, double percentageOfTotal) {
        this.partId = partId;
        this.partName = partName;
        this.quantityRequired = quantityRequired;
        this.quantityAvailable = quantityAvailable;
        this.percentageOfTotal = percentageOfTotal;
    }

    // Getters, Setters
}