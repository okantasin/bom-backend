package com.java.bom.service;

import com.java.bom.dto.project.CreateProjectRequest;
import com.java.bom.dto.project.CreateProjectResponse;
import com.java.bom.dto.project.ProjectResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ProjectService {
     CreateProjectResponse createProject(@Valid CreateProjectRequest request);

      ProjectResponse getProjectById(Long id);

    List<ProjectResponse> getAllProjects();

    void deleteProject(Long id);
}
