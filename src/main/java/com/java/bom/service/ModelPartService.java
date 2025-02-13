package com.java.bom.service;

import com.java.bom.entity.ModelPart;
import com.java.bom.entity.Project;
import com.java.bom.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface ModelPartService {

    ModelPart addPartToModel(Long modelId, Long partId, int quantity);
}
