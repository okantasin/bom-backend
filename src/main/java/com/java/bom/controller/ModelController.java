package com.java.bom.controller;

import com.java.bom.dto.GenericResponse;
import com.java.bom.dto.ModelResponse;
import com.java.bom.entity.Model;
import com.java.bom.entity.ModelPercentage;
import com.java.bom.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/models")
public class ModelController {
    @Autowired
    private ModelService modelService;

    @PostMapping("/addModelsForProject")
    public ResponseEntity<GenericResponse> addModelsForProject(@RequestParam Long projectId,@RequestParam List<String> modelNumber) {
        return ResponseEntity.ok(modelService.addModelsForProject(projectId, modelNumber));
    }

    @GetMapping("/getAllModels")
    public ResponseEntity<List<ModelResponse>> getAllModellist() {
        return ResponseEntity.ok(modelService. getAllModels());
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

    @PostMapping("/deactived-update-status")
    public ResponseEntity<GenericResponse> deactivedModelStatus( @RequestParam Long modelId, @RequestParam Long projectId) {
        GenericResponse response = modelService.deactivateModelStatus(modelId, projectId);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/activeted-update-status")
    public ResponseEntity<GenericResponse> activetedModelStatus(@RequestParam Long modelId, @RequestParam Long projectId) {
        GenericResponse response = modelService.activateModelStatus(modelId, projectId);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/sortedModels")
    public ResponseEntity<List<ModelPercentage>> getSortedModels(
            @RequestParam Long projectId,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate) {

        List<ModelPercentage> sortedModels = modelService.getSortedModelPercentages(projectId, startDate, endDate);
        return ResponseEntity.ok(sortedModels);
    }
}