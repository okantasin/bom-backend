package com.java.bom.service.impl;

import com.java.bom.entity.Model;
import com.java.bom.entity.ModelPart;
import com.java.bom.entity.Part;
import com.java.bom.repository.ModelPartRepository;
import com.java.bom.service.ActionLogService;
import com.java.bom.service.ModelPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelPartServiceImpl implements ModelPartService {
    @Autowired
    private ModelPartRepository modelPartRepository;

    @Autowired
    private ActionLogService actionLogService;
    @Override
    public ModelPart addPartToModel(Long modelId, Long partId, int quantity) {
            ModelPart modelPart = new ModelPart();
            modelPart.setModel(new Model(modelId));
            modelPart.setPart(new Part(partId));
            modelPart.setQuantity(quantity);
            modelPartRepository.save(modelPart);
            actionLogService.logAction("ModelPart", modelPart.getId(), "ADD", "Added part with quantity " + quantity);
            return modelPart;

    }
}
