package com.java.bom.service.impl;

import com.java.bom.entity.Part;
import com.java.bom.entity.common.GeneralStatusEntity;
import com.java.bom.repository.ModelRepository;
import com.java.bom.repository.PartRepository;
import com.java.bom.repository.common.GeneralStatusRepository;
import com.java.bom.service.PartService;
import com.java.bom.utils.RandomCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private GeneralStatusRepository generalStatusRepository;


    public Part addPart(Long modelId, String partName) {

        Part part = new Part();
        part.setName(partName);
        part.setPartNumber(RandomCodeGenerator.generateUUIDCode());
        GeneralStatusEntity defaultStatus = generalStatusRepository.findByShortCode("ACTIVE");
        if(Objects.isNull(defaultStatus)){
            throw new RuntimeException("ACTIVE status not found");
        }
        part.setId(defaultStatus.getId());
        return partRepository.save(part);
    }

    @Override
    public List<Part> getAllPart() {
        List<Part> getAllPart = partRepository.findAll();
        if(getAllPart.isEmpty()){
            return getAllPart;
        }
        return List.of();
    }

    @Override
    public Part getPartById(Long partId) {
        return  partRepository.findById(partId).orElse(null);
    }

    public void deletePart(Long partId) {
        partRepository.deleteById(partId);
    }
}
