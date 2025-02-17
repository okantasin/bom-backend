package com.java.bom.service;

import com.java.bom.dto.ModelPartResponse;
import com.java.bom.entity.ModelPart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface ModelPartService {

    ModelPart addPartToModel(Long modelId, Long partId, int quantity);

    ModelPartResponse getPartsByModel(Long modelId);

    @Transactional
    ModelPart softDeletePartFromModel(Long modelId, Long partId);

    ModelPart updatePartInModel(Long modelId, Long partId, int requiredQuantity);
}
