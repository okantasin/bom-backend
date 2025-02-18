package com.java.bom.controller;
import com.java.bom.entity.Part;
import com.java.bom.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parts")
public class PartController {
    @Autowired
    private PartService partService;

    @PostMapping("/createPart")
    public ResponseEntity<Part> addPart(@RequestParam Long stockQuantity, @RequestParam String partName) {
        return ResponseEntity.ok(partService.addPart(stockQuantity,partName));
    }

    @PostMapping("/list/{partId}")
    public ResponseEntity<Part> getPart(@PathVariable Long partId) {
        return ResponseEntity.ok(partService.getPartById(partId));
    }

    @DeleteMapping("/delete/{partId}")
    public ResponseEntity<String> deletePart(@PathVariable Long partId) {
        partService.deletePart(partId);
        return ResponseEntity.ok("Part deleted successfully");
    }
}