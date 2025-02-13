package com.java.bom.service;

import com.java.bom.entity.Part;

import java.util.List;

public interface PartService {
    Part addPart(Long modelId, String partName, String partNumber);

    List<Part> getPartsByModel(Long modelId);

    void deletePart(Long partId);
}
