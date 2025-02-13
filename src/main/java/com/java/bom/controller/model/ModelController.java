package com.java.bom.controller.model;

import com.java.bom.dto.model.CreateModelRequest;
import com.java.bom.dto.model.CreateModelResponse;
import com.java.bom.dto.model.ModelResponse;
import com.java.bom.service.ModelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {

    @Autowired
    private final ModelService  modelService;

    @PostMapping("/createModel")
    public ResponseEntity<CreateModelResponse> createProject(@Valid @RequestBody CreateModelRequest request) {
        return ResponseEntity.ok(modelService.createModel(request));
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<ModelResponse> getProjectById(@PathVariable Long projectId) {
        return ResponseEntity.ok(modelService.getModelProjectById(projectId));
    }

    @GetMapping
    public ResponseEntity<List<ModelResponse>> getAllProjects() {
        return ResponseEntity.ok(modelService.getAllModels());
    }

    @DeleteMapping("/{modelId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long modelId) {
        modelService.deleteModel(modelId);
        return ResponseEntity.noContent().build();
    }
}
