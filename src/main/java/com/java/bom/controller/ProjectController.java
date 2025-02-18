package com.java.bom.controller;

import com.java.bom.dto.GenericResponse;
import com.java.bom.entity.Project;
import com.java.bom.service.ProjectService;
import com.java.bom.utils.PlanningType;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@Tag(name = "User Controller", description = "User işlemleri için API")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/createProject")
    public ResponseEntity<GenericResponse> addProject(@RequestBody Project project) {
        return ResponseEntity.ok(projectService.addProject(project));
    }

    @PutMapping("/update-planning-type")
    public ResponseEntity<GenericResponse> updatePlanningType(@RequestParam Long projectId, @RequestParam PlanningType newPlanningType) {
        return ResponseEntity.ok( projectService.updatePlanningType(projectId, newPlanningType));
    }

}
