package com.java.bom.service.impl;

import com.java.bom.dto.ModelPercentageData;
import com.java.bom.dto.ModelPercentageRequest;
import com.java.bom.entity.Model;
import com.java.bom.entity.ModelConfig;
import com.java.bom.entity.ModelPercentage;
import com.java.bom.entity.Project;
import com.java.bom.repository.ModelConfigRepository;
import com.java.bom.repository.ModelPercentageRepository;
import com.java.bom.repository.ModelRepository;
import com.java.bom.repository.ProjectRepository;
import com.java.bom.service.ModelPercentageService;
import com.java.bom.utils.PlanningType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class ModelPercentageServiceImpl implements ModelPercentageService {

    @Autowired
    private ModelPercentageRepository modelPercentageRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelConfigRepository modelConfigRepository;

    @Autowired
    private ProjectRepository projectRepository;


    @Transactional
    public void saveModelPercentages(ModelPercentageRequest request) {

        Optional<Project> project = projectRepository.findById(request.getProjectId());
        if (project.isEmpty()) {
            throw new RuntimeException("Project mevcut değil");
        }
        // Model ID'leri ve yüzdeleri eşleştir
        for (ModelPercentageData data : request.getModelPercentageList()) {
            // Model ID'ye karşılık gelen Model nesnesini bul

            Optional<ModelConfig> config = modelConfigRepository.findByModelNumber(data.getModelNumber());
            Optional<Model> model = modelRepository.findModelByModelNumberAndProject_Id(config.get().getModelNumber(),request.getProjectId());
            // Model yüzdelerini kaydet
            saveModelPercentage(project.get(),model.get(), data, request.getYear(), request.getWeek(), request.getMonth());
        }
    }

    private void saveModelPercentage(Project project, Model model, ModelPercentageData data, int year,Integer week,Integer month) {
        PlanningType planningType = project.getPlanningType();
        if (planningType == PlanningType.AYLIK && (month == null || month < 1 || month > 12)) {
            throw new IllegalArgumentException("Aylık planlama için geçerli bir ay (1-12) belirtilmelidir.");
        }

        if (planningType == PlanningType.HAFTALIK && (week == null || week < 1 || week > 5)) {
            throw new IllegalArgumentException("Haftalık planlama için geçerli bir hafta (1-5) belirtilmelidir.");
        }

        ModelPercentage modelPercentage = new ModelPercentage();
        modelPercentage.setModel(model);
        modelPercentage.setPercentage(data.getPercentage());
        modelPercentage.setPlanningType(project.getPlanningType());
        modelPercentage.setYear(year);
        modelPercentage.setMonth(!planningType.equals(PlanningType.SABIT) ? month : null);
        modelPercentage.setProject(project);
        modelPercentage.setWeek(!planningType.equals(PlanningType.SABIT) ? week : null);

        modelPercentageRepository.save(modelPercentage);
    }


}