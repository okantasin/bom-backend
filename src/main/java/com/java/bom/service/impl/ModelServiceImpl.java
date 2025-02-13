package com.java.bom.service.impl;

import com.java.bom.entity.Model;
import com.java.bom.entity.Project;
import com.java.bom.repository.ModelRepository;
import com.java.bom.repository.ProjectRepository;
import com.java.bom.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ModelServiceImpl  implements ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public Model addModel(Long projectId, String modelName) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Model model = new Model();
        model.setName(modelName);
        model.setProject(project);

        return modelRepository.save(model);
    }

    public List<Model> getModelsByProject(Long projectId) {
        return modelRepository.findByProjectId(projectId);
    }

    public void deleteModel(Long modelId) {
        modelRepository.deleteById(modelId);
    }
}
