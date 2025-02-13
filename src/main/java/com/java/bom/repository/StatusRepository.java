package com.java.bom.repository;

import com.java.bom.entity.common.GeneralStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<GeneralStatusEntity, Long> {

    GeneralStatusEntity findByShortCodeAndEntityCodeName(String shortCode, String entityCodeName);
}
