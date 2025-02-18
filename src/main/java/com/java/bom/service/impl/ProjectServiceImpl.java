package com.java.bom.service.impl;

import com.java.bom.dto.GenericResponse;
import com.java.bom.entity.Project;

import com.java.bom.entity.common.GeneralStatusEntity;
import com.java.bom.repository.ProjectRepository;
import com.java.bom.repository.common.GeneralStatusRepository;
import com.java.bom.service.ActionLogService;
import com.java.bom.service.ProjectService;
import com.java.bom.utils.PlanningType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ActionLogService actionLogService;
    @Autowired
    private GeneralStatusRepository generalStatusRepository;

    @Override
    @Transactional
    public GenericResponse addProject(Project project) {
            GeneralStatusEntity defaultStatus = generalStatusRepository.findByShortCode("ACTIVE");
            if(Objects.isNull(defaultStatus)){
                throw new RuntimeException("ACTIVE status not found");
            }
            project.setStatusId(defaultStatus.getId());
            project.setStartDate(LocalDate.now());
            project.setEndDate(LocalDate.now().plusMonths(12));
        try{
            projectRepository.save(project);
        }catch (Exception e){
            actionLogService.logAction("Project", project.getId(), "ERROR", "Proje ekleme işlemi başarısız!");
            return new GenericResponse<>(false, "İşlem başarısız");
        }

        return new GenericResponse<>(true, "İşlem başarıyla tamamlandı");
    }

    @Override
    public Project getProject(Long projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));
    }

    @Override
    public GenericResponse updatePlanningType(Long projectId, PlanningType newPlanningType) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setPreviousPlanningType(project.getPlanningType());
        project.setPlanningType(newPlanningType);
        try{
            projectRepository.save(project);
        }catch (Exception e){
            actionLogService.logAction("Project", projectId, "ERROR", "Proje planı güncellenemedi!");
            return new GenericResponse<>(false, "İşlem başarısız");
        }
        actionLogService.logAction("Project", projectId, "UPDATE", "Plan değişti " + newPlanningType);
        return new GenericResponse<>(true,"Plan tipi başarılı bir şekile güncellendi");
    }
}
