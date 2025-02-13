package com.java.bom.controller;


import com.java.bom.entity.ModelPart;
import com.java.bom.service.ModelPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/model-parts")
public class ModelPartController {

    @Autowired
    private ModelPartService modelPartService;

    @PostMapping("/add")
    public ResponseEntity<ModelPart> addPartToModel(@RequestParam Long modelId, @RequestParam Long partId, @RequestParam int quantity) {
        return ResponseEntity.ok(modelPartService.addPartToModel(modelId, partId, quantity));
    }


}