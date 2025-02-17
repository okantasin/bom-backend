package com.java.bom.service;

import com.java.bom.entity.Part;

import java.util.List;


public interface PartService {
    Part addPart(Long modelId, String partName);
    List<Part> getAllPart();
    Part getPartById(Long partId);

    void deletePart(Long partId);
}
