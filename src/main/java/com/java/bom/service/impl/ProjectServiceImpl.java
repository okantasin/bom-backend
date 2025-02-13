package com.java.bom.service.impl;

import com.java.bom.dto.project.CreateProjectRequest;
import com.java.bom.dto.project.CreateProjectResponse;
import com.java.bom.dto.project.ProjectResponse;
import com.java.bom.entity.ProjectEntity;
import com.java.bom.entity.common.GeneralStatusEntity;
import com.java.bom.entity.common.GeneralTypeEntity;
import com.java.bom.repository.GeneralTypeRepository;
import com.java.bom.repository.ProjectRepository;
import com.java.bom.repository.StatusRepository;
import com.java.bom.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private GeneralTypeRepository generalTypeRepository;
    @Override
    public CreateProjectResponse createProject(CreateProjectRequest request) {

        if(request.getName() == null || request.getName().isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be empty");
        }
        if (projectRepository.findByName(request.getName()).isPresent()) {
            throw new IllegalArgumentException("Project with name '" + request.getName() + "' already exists.");
        }


        GeneralTypeEntity configurationType = generalTypeRepository.findByShortCodeAndEntityCodeName(request.getConfigType(),"PROJECT");
        if(configurationType == null) {
            throw new IllegalArgumentException("Project with name '" + request.getName() + "' does not exist.");
        }

        ProjectEntity project = new ProjectEntity();
        project.setName(request.getName());
        project.setConfigTypeId(configurationType.getTypeId());
        GeneralStatusEntity activeStatus = statusRepository.findByShortCodeAndEntityCodeName("ACTIVE","PROJECT");
        if(activeStatus == null) {
            throw new IllegalArgumentException("Project with name '" + request.getName() + "' does not exist.");
        }
        project.setStId(activeStatus.getStatusId());
        ProjectEntity savedProject = projectRepository.save(project);

        // Return response with ID
        return CreateProjectResponse.builder()
                .projectId(String.valueOf(savedProject.getProjectId()))
                .configType(configurationType.getShortCode())
                .name(savedProject.getName())
                .build();
    }



    @Override
    public ProjectResponse getProjectById(Long id) {
        return null;
    }

    @Override
    public List<ProjectResponse> getAllProjects() {
        return List.of();
    }

    @Override
    public void deleteProject(Long id) {

    }
}
