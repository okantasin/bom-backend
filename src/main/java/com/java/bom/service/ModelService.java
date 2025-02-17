package com.java.bom.service;

import com.java.bom.dto.GenericResponse;
import com.java.bom.entity.Model;

import java.util.List;

public interface ModelService {

    GenericResponse updateModelStatus(Long modelId, Long projectId);

    GenericResponse addModelsForProject(Long projectId, List<String> modelName);

    List<Model> getAllModels(Long projectId);

    Model getModelById(Long modelId);

    void deleteModel(Long modelId);
}
