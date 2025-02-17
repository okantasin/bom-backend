package com.java.bom.service;

import com.java.bom.dto.ModelProductionResult;

import java.util.List;

public interface ProductionCalculationService {

    List<ModelProductionResult> initializeProduction(Long projectId);
}
