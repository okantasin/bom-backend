package com.java.bom.controller;

import com.java.bom.dto.ModelPartResponse;
import com.java.bom.dto.ModelPercentageRequest;
import com.java.bom.service.ModelPercentageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/model-percentage")
public class ModelPercentageController {

    @Autowired
    private ModelPercentageService modelPercentageService;

    @PostMapping("/addModelPercentages")
    public ResponseEntity<String> saveModelPercentages(@RequestBody ModelPercentageRequest request) {
        modelPercentageService.saveModelPercentages(request);
        return ResponseEntity.ok("Model yüzdeleri başarıyla kaydedildi.");
    }
}