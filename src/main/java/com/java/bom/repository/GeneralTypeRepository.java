package com.java.bom.repository;

import com.java.bom.entity.common.GeneralStatusEntity;
import com.java.bom.entity.common.GeneralTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralTypeRepository extends JpaRepository<GeneralTypeEntity,Long> {

    GeneralTypeEntity findByShortCodeAndEntityCodeName(String shortCode, String entityCodeName);
}
