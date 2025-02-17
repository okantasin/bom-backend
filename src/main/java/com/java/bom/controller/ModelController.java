package com.java.bom.controller;

import com.java.bom.dto.GenericResponse;
import com.java.bom.entity.Model;
import com.java.bom.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @PostMapping("/addModelsForProject")
    public ResponseEntity<GenericResponse> addModelsForProject(@RequestParam Long projectId, @RequestParam List<String> modelName) {
        return ResponseEntity.ok(modelService.addModelsForProject(projectId, modelName));
    }

    @GetMapping("/list/{modelId}")
    public ResponseEntity<List<Model>> getModelsByProject(@PathVariable Long modelId) {
        return ResponseEntity.ok(modelService.getAllModels(modelId));
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<Model> getModelById(@PathVariable Long modelId) {
        return ResponseEntity.ok(modelService.getModelById(modelId));
    }

    @DeleteMapping("/delete/{modelId}")
    public ResponseEntity<String> deleteModel(@PathVariable Long modelId) {
        modelService.deleteModel(modelId);
        return ResponseEntity.ok("Model deleted successfully");
    }
}