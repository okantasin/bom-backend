package com.java.bom.service.impl;

import com.java.bom.dto.GenericResponse;
import com.java.bom.entity.Model;
import com.java.bom.entity.Project;
import com.java.bom.entity.common.GeneralStatusEntity;
import com.java.bom.repository.ModelRepository;
import com.java.bom.repository.ProjectRepository;
import com.java.bom.repository.common.GeneralStatusRepository;
import com.java.bom.service.ActionLogService;
import com.java.bom.service.ModelService;
import com.java.bom.utils.PlanningType;
import com.java.bom.utils.RandomCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


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


    @Override
    public GenericResponse updateModelStatus(Long modelId, Long projectId) {
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
    public GenericResponse addModelsForProject(Long projectId, List<String> modelName) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<Model> models =  modelName.stream().map(m-> {
            Model model = new Model();
            model.setName(m);
            GeneralStatusEntity defaultStatus = generalStatusRepository.findByShortCode("ACTIVE");
            model.setModelNumber(RandomCodeGenerator.generateUUIDCode());
            model.setStatusId(defaultStatus.getId());
            model.setProject(project);
            return model;

        }).toList();

       try {
           modelRepository.saveAll(models).forEach(model -> {actionLogService.logAction("ModelPart", model.getId(), "ADD", model.getId() + "Başarıyla eklendi." );});

       }catch (Exception e){
           models.forEach(model -> {actionLogService.logAction("ModelPart", model.getId(), "ERROR", e.getMessage());});
       }
        return new GenericResponse<>(true, "Model başarıyla eklendi");
    }

    public List<Model> getAllModels(Long projectId) {
        return modelRepository.findByProjectId(projectId);
    }

    @Override
    public Model getModelById(Long modelId) {
        return modelRepository.findById(modelId).orElse(null);
    }

    public void deleteModel(Long modelId) {
        modelRepository.deleteById(modelId);
    }
}
