package com.java.bom.service.impl;

import com.java.bom.dto.ModelProductionResult;
import com.java.bom.dto.PartDetail;
import com.java.bom.dto.ProductionData;
import com.java.bom.entity.Model;
import com.java.bom.entity.ModelPart;
import com.java.bom.entity.ModelPercentage;
import com.java.bom.entity.Project;
import com.java.bom.repository.ModelPercentageRepository;
import com.java.bom.service.ProductionCalculationService;
import com.java.bom.service.ProjectService;
import com.java.bom.utils.PlanningType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.java.bom.utils.PlanningType.SABIT;

@Service
public class ProductionCalculationImpl implements ProductionCalculationService {
    @Autowired
    private ProjectService projectService;
    @Override
    public List<ModelProductionResult> initializeProduction(Long projectId) {
        Project project = projectService.getProject(projectId);

        PlanningType planningType = project.getPlanningType();
        if(SABIT.equals(planningType)){

        }

        return List.of();
    }

}
