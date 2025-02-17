package com.java.bom.service.impl;

import com.java.bom.dto.ModelPercentageData;
import com.java.bom.dto.ModelPercentageRequest;
import com.java.bom.entity.Model;
import com.java.bom.entity.ModelPercentage;
import com.java.bom.repository.ModelPercentageRepository;
import com.java.bom.repository.ModelRepository;
import com.java.bom.service.ModelPercentageService;
import com.java.bom.utils.PlanningType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;

@Service
public class ModelPercentageServiceImpl implements ModelPercentageService {

    @Autowired
    private ModelPercentageRepository modelPercentageRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Transactional
    public void saveModelPercentages(ModelPercentageRequest request) {
        // Model ID'leri ve yüzdeleri eşleştir
        for (ModelPercentageData data : request.getModelPercentageList()) {
            // Model ID'ye karşılık gelen Model nesnesini bul
            Model model = modelRepository.findById(data.getModelId())
                    .orElseThrow(() -> new RuntimeException("Model bulunamadı: " + data.getModelId()));

            // Model yüzdelerini kaydet
            saveModelPercentage(model, data);
        }
    }

    private void saveModelPercentage(Model model, ModelPercentageData data) {
        if (data.getPlanningType() == PlanningType.AYLIK && (data.getMonth() == null || data.getMonth() < 1 || data.getMonth() > 12)) {
            throw new IllegalArgumentException("Aylık planlama için geçerli bir ay (1-12) belirtilmelidir.");
        }

        if (data.getPlanningType() == PlanningType.HAFTALIK && (data.getWeek() == null || data.getWeek() < 1 || data.getWeek() > 5)) {
            throw new IllegalArgumentException("Haftalık planlama için geçerli bir hafta (1-5) belirtilmelidir.");
        }

        ModelPercentage modelPercentage = new ModelPercentage();
        modelPercentage.setModel(model);
        modelPercentage.setPercentage(data.getPercentage());
        modelPercentage.setPlanningType(data.getPlanningType());
        modelPercentage.setYear(data.getYear());
        modelPercentage.setMonth(data.getMonth());
        modelPercentage.setWeek(data.getWeek());

        modelPercentageRepository.save(modelPercentage);
    }
}