package com.java.bom.service.impl;

import com.java.bom.dto.model.CreateModelRequest;
import com.java.bom.dto.model.CreateModelResponse;
import com.java.bom.dto.model.ModelResponse;
import com.java.bom.repository.ModelRepository;
import com.java.bom.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public void deleteModel(Long id) {

    }

    @Override
    public List<ModelResponse> getAllModels() {
        return List.of();
    }

    @Override
    public ModelResponse getModelProjectById(Long projectId) {
        return null;
    }

    @Override
    public CreateModelResponse createModel(CreateModelRequest request) {
        return null;
    }
}
