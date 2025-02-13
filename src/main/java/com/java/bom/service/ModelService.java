package com.java.bom.service;

import com.java.bom.dto.model.CreateModelRequest;
import com.java.bom.dto.model.CreateModelResponse;
import com.java.bom.dto.model.ModelResponse;
import com.java.bom.dto.project.CreateProjectRequest;
import com.java.bom.dto.project.CreateProjectResponse;
import com.java.bom.dto.project.ProjectResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ModelService {

    void deleteModel(Long id);

    List<ModelResponse> getAllModels();

    ModelResponse getModelProjectById(Long projectId);

    CreateModelResponse createModel(@Valid CreateModelRequest request);
}
