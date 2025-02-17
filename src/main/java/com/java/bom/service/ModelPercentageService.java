package com.java.bom.service;

import com.java.bom.dto.ModelPercentageRequest;
import org.springframework.transaction.annotation.Transactional;

public interface ModelPercentageService {

    @Transactional
    void saveModelPercentages(ModelPercentageRequest request);
}
