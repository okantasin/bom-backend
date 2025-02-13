package com.java.bom.controller;

import com.java.bom.service.ProjectService;
import com.java.bom.utils.PlanningType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PutMapping("/update-planning-type")
    public ResponseEntity<String> updatePlanningType(@RequestParam Long projectId, @RequestParam PlanningType newPlanningType) {
        projectService.updatePlanningType(projectId, newPlanningType);
        return ResponseEntity.ok("Planning type updated successfully.");
    }
}