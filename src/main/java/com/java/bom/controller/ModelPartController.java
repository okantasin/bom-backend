package com.java.bom.controller;


import com.java.bom.dto.ModelPartResponse;
import com.java.bom.entity.ModelPart;
import com.java.bom.service.ModelPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/model-parts")
public class ModelPartController {

    @Autowired
    private ModelPartService modelPartService;

    @PostMapping("/addPartToModel")
    public ResponseEntity<ModelPart> addPartToModel(@RequestParam Long modelId, @RequestParam Long partId, @RequestParam int quantity) {
        return ResponseEntity.ok(modelPartService.addPartToModel(modelId, partId, quantity));
    }
    @GetMapping("/{modelId}/getParts")
    public ResponseEntity<ModelPartResponse> getPartsByModel(@PathVariable Long modelId) {
        return ResponseEntity.ok(modelPartService.getPartsByModel(modelId));
    }

    @PutMapping("/{modelId}/deletePart/{partId}")
    public ResponseEntity<ModelPart> softDeletePartFromModel(
            @PathVariable Long modelId,
            @PathVariable Long partId) {

        return ResponseEntity.ok(modelPartService.softDeletePartFromModel(modelId, partId));
    }

}