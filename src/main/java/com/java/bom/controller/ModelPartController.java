package com.java.bom.controller;


import com.java.bom.dto.ModelPartProductionResult;
import com.java.bom.dto.ModelPartResponse;
import com.java.bom.dto.PartProductionRequest;
import com.java.bom.entity.ModelPart;
import com.java.bom.service.ModelPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model-parts")
public class ModelPartController {

    @Autowired
    private ModelPartService modelPartService;

    @PostMapping("/addPartToModel")
    public ResponseEntity<ModelPart> addPartToModel(@RequestParam Long modelId, @RequestParam Long partId, @RequestParam int quantity) {
        return ResponseEntity.ok(modelPartService.addPartToModel(modelId, partId, quantity));
    }
    @GetMapping("/{projectId}/parts/{modelNumber}")
    public ResponseEntity<ModelPartResponse> getPartsByModel(
            @PathVariable Long projectId,
            @PathVariable String modelNumber) {

        try {
            ModelPartResponse response = modelPartService.getPartsByModel(projectId, modelNumber);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ModelPartResponse("Error: " + e.getMessage()));
        }
    }
    @PutMapping("/{modelId}/deletePart/{partId}")
    public ResponseEntity<ModelPart> softDeletePartFromModel(
            @PathVariable Long modelId,
            @PathVariable Long partId) {

        return ResponseEntity.ok(modelPartService.softDeletePartFromModel(modelId, partId));
    }
    @PostMapping("/{projectId}/calculate-parts")
    public ResponseEntity<List<ModelPartProductionResult>> calculatePartProduction(
            @PathVariable Long projectId,
            @RequestBody PartProductionRequest request) {

        try {
            List<ModelPartProductionResult> results = modelPartService.calculatePartProduction(
                    projectId,
                    request.getTotalProduction(),
                    request.getYear(),
                    request.getMonth(),
                    request.getWeek());

            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}