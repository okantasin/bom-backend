package com.java.bom.service;

import com.java.bom.entity.Model;

import java.util.List;

public interface ModelService {

    Model addModel(Long projectId, String modelName);

    List<Model> getModelsByProject(Long projectId);

    void deleteModel(Long modelId);
}
