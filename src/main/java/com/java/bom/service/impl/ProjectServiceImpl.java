package com.java.bom.service.impl;

import com.java.bom.entity.Project;

import com.java.bom.repository.ProjectRepository;
import com.java.bom.service.ActionLogService;
import com.java.bom.service.ProjectService;
import com.java.bom.utils.PlanningType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ActionLogService actionLogService;

    @Override
    public void updatePlanningType(Long projectId, PlanningType newPlanningType) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setPreviousPlanningType(project.getPlanningType());
        project.setPlanningType(newPlanningType);
        projectRepository.save(project);

        actionLogService.logAction("Project", projectId, "UPDATE", "Changed planning type to " + newPlanningType);
    }
}
