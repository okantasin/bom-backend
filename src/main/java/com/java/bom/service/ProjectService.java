package com.java.bom.service;

import com.java.bom.dto.project.CreateProjectRequest;
import com.java.bom.dto.project.CreateProjectResponse;
import com.java.bom.dto.project.ProjectResponse;
import com.java.bom.utils.PlanningType;
import jakarta.validation.Valid;

import java.util.List;

public interface ProjectService {

    void updatePlanningType(Long projectId, PlanningType newPlanningType);
}
