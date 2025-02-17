package com.java.bom.controller;

import com.java.bom.dto.ModelProductionResult;
import com.java.bom.service.ProductionCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.bom.dto.ModelProductionResult;
import com.java.bom.service.ProductionCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/production")
@Tag(name = "Production Management", description = "Handles production process")
public class ProductionController {

    @Autowired
    private ProductionCalculationService productionCalculationService;

    @Operation(summary = "Initialize production process", description = "Prepares production for a given project.")
    @Transactional
    @GetMapping("/initialize")
    public ResponseEntity<List<ModelProductionResult>> initialize(@RequestParam Long projectId) {
     List<ModelProductionResult> result = productionCalculationService.initializeProduction(projectId);
        return null;
    }

    @Operation(summary = "Update production status", description = "Updates production based on progress or changes.")
    @Transactional
    @PutMapping("/update")
    public ResponseEntity<List<ModelProductionResult>> update(@RequestParam Long projectId) {
  //      List<ModelProductionResult> result = productionCalculationService.updateProduction(projectId);
  //      return ResponseEntity.ok(result);
        return null;

    }

    @Operation(summary = "Submit production results", description = "Finalizes the production process for a project.")
    @Transactional
    @PostMapping("/submit")
    public ResponseEntity<List<ModelProductionResult>> submit(@RequestParam Long projectId) {
  //      List<ModelProductionResult> result = productionCalculationService.submitProduction(projectId);
   //     return ResponseEntity.ok(result);
        return null;

    }
}