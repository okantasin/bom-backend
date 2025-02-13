package com.java.bom.service.impl;

import com.java.bom.entity.Model;
import com.java.bom.entity.Part;
import com.java.bom.repository.ModelRepository;
import com.java.bom.repository.PartRepository;
import com.java.bom.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PartServiceImpl implements PartService {

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private ModelRepository modelRepository;

    public Part addPart(Long modelId, String partName, String partNumber) {
        Model model = modelRepository.findById(modelId)
                .orElseThrow(() -> new RuntimeException("Model not found"));

        Part part = new Part();
        part.setName(partName);
        part.setPartNumber(partNumber);
      //  part.setModel(model);

        return partRepository.save(part);
    }

    public List<Part> getPartsByModel(Long modelId) {
        return partRepository.findByModelId(modelId);
    }

    public void deletePart(Long partId) {
        partRepository.deleteById(partId);
    }
}
