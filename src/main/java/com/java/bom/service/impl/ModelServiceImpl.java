package com.java.bom.service.impl;

import com.java.bom.dto.GenericResponse;
import com.java.bom.dto.ModelResponse;
import com.java.bom.entity.Model;
import com.java.bom.entity.ModelPercentage;
import com.java.bom.entity.Project;
import com.java.bom.entity.common.GeneralStatusEntity;
import com.java.bom.repository.ModelConfigRepository;
import com.java.bom.repository.ModelPercentageRepository;
import com.java.bom.repository.ModelRepository;
import com.java.bom.repository.ProjectRepository;
import com.java.bom.repository.common.GeneralStatusRepository;
import com.java.bom.service.ActionLogService;
import com.java.bom.service.ModelService;
import com.java.bom.utils.PlanningType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ModelServiceImpl  implements ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private GeneralStatusRepository generalStatusRepository;

    @Autowired
    private ActionLogService actionLogService;

    @Autowired
    private ModelPercentageRepository modelPercentageRepository;
    @Autowired
    private ModelConfigRepository modelConfigRepository;


    @Override
    public GenericResponse deactivateModelStatus(Long modelId, Long projectId) {
        PlanningType planningType = modelRepository.findProjectByModeId(modelId,projectId).orElseThrow(() -> new RuntimeException("Model not found with ID: " + modelId));
         if (planningType == PlanningType.AYLIK || planningType == PlanningType.HAFTALIK) {
            throw new RuntimeException("Bu model update edilemez");
        }
        Optional<Model> model = modelRepository.findModelByProject_IdAndId(projectId,modelId);
         if (model.isEmpty()) {
             throw new RuntimeException("Bu model proje içinde mevcut değil: " + modelId);
         }
        GeneralStatusEntity defaultStatus = generalStatusRepository.findByShortCode("INACTIVE");
        model.get().setStatusId(defaultStatus.getId());
        modelRepository.save(model.get());
        return new GenericResponse(true,"Model güncellemesi başarılı.");
    }


    @Override
    public GenericResponse activateModelStatus(Long modelId, Long projectId) {
        PlanningType planningType = modelRepository.findProjectByModeId(modelId,projectId).orElseThrow(() -> new RuntimeException("Model not found with ID: " + modelId));
        if (planningType == PlanningType.AYLIK || planningType == PlanningType.HAFTALIK) {
            throw new RuntimeException("Bu model update edilemez");
        }
        Optional<Model> model = modelRepository.findModelById(modelId);
        GeneralStatusEntity defaultStatus = generalStatusRepository.findByShortCode("DEACTIVE");
        model.get().setStatusId(defaultStatus.getId());
        modelRepository.save(model.get());
        return new GenericResponse(true,"Model güncellemesi başarılı.");
    }

    public GenericResponse addModelsForProject(Long projectId, List<String> modelNumber) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Proje bulunamadı"));
        List<Model> models = modelNumber.stream()
                .map(conf -> modelConfigRepository.findByModelNumber(conf)
                        .map(mc -> {
                            Model model = new Model();
                            model.setName(mc.getModelName());
                            model.setModelNumber(mc.getModelNumber());
                            model.setStatusId(100);
                            model.setProject(project);
                            return model;
                        }).orElse(null))
                .filter(Objects::nonNull)
                .toList();

       try {
           modelRepository.saveAll(models).forEach(model -> {actionLogService.logAction("ModelPart", model.getId(), "ADD", model.getId() + "Başarıyla eklendi." );});

       }catch (Exception e){
           models.forEach(model -> {actionLogService.logAction("ModelPart", model.getId(), "ERROR", e.getMessage());});
       }
        return new GenericResponse<>(true, "Model başarıyla eklendi");
    }

    public List<ModelResponse> getAllModels() {
        List<ModelResponse> responses = new ArrayList<>();
        List<Model> model = modelRepository.findAll();
        model.forEach(m -> {
            ModelResponse modelResponse = new ModelResponse();
            modelResponse.setModelName(m.getName());
            modelResponse.setModelNumber(m.getModelNumber());
            modelResponse.setModelPercentages(m.getModelPercentages().get(0).getPercentage());
            GeneralStatusEntity defaultStatus = generalStatusRepository.findById(m.getStatusId()).get();
            modelResponse.setStatus(defaultStatus.getShortCode());
            modelResponse.setProjectName(m.getProject().getName());
            responses.add(modelResponse);
        });

        return responses ;
    }

    @Override
    public Model getModelById(Long modelId) {
        return modelRepository.findById(modelId).orElse(null);
    }

    @Override
    public List<Model> getModelsByProjectIdAndModel(Long projectId) {
        return List.of();
    }

    @Override
    public List<Model> getModelsByProjectId(Long projectId) {
        return modelRepository.findModelByProject_Id(projectId);
    }

    public void deleteModel(Long modelId) {
        modelRepository.deleteById(modelId);
    }

    @Override
    public List<ModelPercentage> getSortedModelPercentages(Long projectId, LocalDate startDate, LocalDate endDate) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));

        //List<Model> models = modelRepository.findByProjectId(projectId);
        List<ModelPercentage> modelPercentage = modelPercentageRepository.findModelPercentageByProjectId(projectId);

        PlanningType planningType = project.getPlanningType();

        if (planningType == PlanningType.AYLIK || planningType == PlanningType.HAFTALIK) {
            // Eğer tarih aralığı belirtilmediyse, projenin ilk tarih aralığını kullan
            if (startDate == null || endDate == null) {
                startDate = project.getStartDate();
                endDate = project.getEndDate();
            }

            // Model yüzdeliklerini belirtilen tarih aralığında al ve büyükten küçüğe sırala
            LocalDate finalStartDate = startDate;
            LocalDate finalEndDate = endDate;
            return modelPercentage.stream()
                    .filter(model -> isModelWithinDateRange(model.getModel(), finalStartDate, finalEndDate))
                    .sorted((m1, m2) -> Double.compare(m2.getPercentage(), m1.getPercentage()))
                    .collect(Collectors.toList());

        } else if (planningType == PlanningType.SABIT) {
            // Eğer planlama türü SABİT ise, tarih aralığı önemli değildir, sadece yüzdelik değerlerine göre sırala

            return modelPercentage.stream()
                    .sorted((m1, m2) -> Double.compare(m2.getPercentage(), m1.getPercentage()))
                    .collect(Collectors.toList());
        }

        throw new RuntimeException("Geçersiz planlama türü");
    }

  private boolean isModelWithinDateRange(Model model, LocalDate startDate, LocalDate endDate) {
        return (model.getCreatedAt().isAfter(startDate.atStartOfDay()) || model.getCreatedAt().isEqual(startDate.atStartOfDay())) &&
                (model.getCreatedAt().isBefore(endDate.atStartOfDay()) || model.getCreatedAt().isEqual(endDate.atStartOfDay()));
    }
}
