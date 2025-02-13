package com.java.bom.service;

import com.java.bom.dto.part.CreatePartRequest;
import com.java.bom.dto.part.PartResponse;

public interface PartService {

    PartResponse createPart(CreatePartRequest createPartRequest);


}
