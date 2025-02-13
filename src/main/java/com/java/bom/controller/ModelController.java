package com.java.bom.controller;

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

    @PostMapping("/add")
    public ResponseEntity<Model> addModel(@RequestParam Long projectId, @RequestParam String modelName) {
        return ResponseEntity.ok(modelService.addModel(projectId, modelName));
    }

    @GetMapping("/list/{projectId}")
    public ResponseEntity<List<Model>> getModelsByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(modelService.getModelsByProject(projectId));
    }

    @DeleteMapping("/delete/{modelId}")
    public ResponseEntity<String> deleteModel(@PathVariable Long modelId) {
        modelService.deleteModel(modelId);
        return ResponseEntity.ok("Model deleted successfully");
    }
}