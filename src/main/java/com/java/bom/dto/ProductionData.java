package com.java.bom.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ProductionData {
    private Long modelId;
    private String modelName;
    private String productionStatus; // e.g., "IN_PROGRESS", "COMPLETED", "PENDING"

    private List<PartDetail> parts;
    private double completionPercentage; // 0-100% arasında tamamlanma oranı

    private double totalCost;
    private int missingPartsCount;
    private boolean isOrderPlaced; // Eksik parçalar için sipariş verildi mi?

    private LocalDateTime startTime;
    private LocalDateTime lastUpdatedTime;
    private LocalDateTime estimatedCompletionTime;

    private Long projectId;
    private String projectName;

    // Constructor, Getters, Setters
}