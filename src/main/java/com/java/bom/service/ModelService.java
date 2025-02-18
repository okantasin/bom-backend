package com.java.bom.service;

import com.java.bom.dto.GenericResponse;
import com.java.bom.dto.ModelResponse;
import com.java.bom.entity.Model;
import com.java.bom.entity.ModelPercentage;

import java.time.LocalDate;
import java.util.List;

public interface ModelService {

    GenericResponse deactivateModelStatus(Long modelId, Long projectId);

    GenericResponse activateModelStatus(Long modelId, Long projectId);

    GenericResponse addModelsForProject(Long projectId, List<String> modelNumber);

    List<ModelResponse> getAllModels();

    Model getModelById(Long modelId);

    List<Model> getModelsByProjectIdAndModel(Long projectId);


    List<Model> getModelsByProjectId(Long projectId);

    void deleteModel(Long modelId);

    List<ModelPercentage> getSortedModelPercentages(Long projectId, LocalDate startDate, LocalDate endDate);

}
