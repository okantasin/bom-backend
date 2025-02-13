package com.java.bom.controller;
import com.java.bom.entity.Part;
import com.java.bom.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class PartController {

    @Autowired
    private PartService partService;

    @PostMapping("/add")
    public ResponseEntity<Part> addPart(@RequestParam Long modelId, @RequestParam String partName, @RequestParam String partNumber) {
        return ResponseEntity.ok(partService.addPart(modelId, partName, partNumber));
    }

    @GetMapping("/list/{modelId}")
    public ResponseEntity<List<Part>> getPartsByModel(@PathVariable Long modelId) {
        return ResponseEntity.ok(partService.getPartsByModel(modelId));
    }

    @DeleteMapping("/delete/{partId}")
    public ResponseEntity<String> deletePart(@PathVariable Long partId) {
        partService.deletePart(partId);
        return ResponseEntity.ok("Part deleted successfully");
    }
}