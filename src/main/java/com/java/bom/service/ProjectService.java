package com.java.bom.service;


import com.java.bom.dto.GenericResponse;
import com.java.bom.entity.Project;
import com.java.bom.utils.PlanningType;

public interface ProjectService {

    GenericResponse updatePlanningType(Long projectId, PlanningType newPlanningType);

    GenericResponse addProject(Project project);

    Project getProject(Long projectId);

}
