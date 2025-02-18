package com.java.bom.service;

import com.java.bom.dto.ModelPartProductionResult;
import com.java.bom.dto.ModelPartResponse;
import com.java.bom.entity.ModelPart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ModelPartService {

    ModelPart addPartToModel(Long modelId, Long partId, int quantity);

    ModelPartResponse getPartsByModel(Long modelId);

    ModelPartResponse getPartsByModel(Long projectId, String modelNumber);

    @Transactional
    ModelPart softDeletePartFromModel(Long modelId, Long partId);

    ModelPart updatePartInModel(Long modelId, Long partId, int requiredQuantity);

    ModelPart getPercentance(Long modelId, Long partId);

     List<ModelPartProductionResult>  calculatePartProduction(Long projectId, int totalProduction, int year, Integer month, Integer week);
}
