package com.java.bom.repository.common;

import com.java.bom.entity.common.GeneralTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralTypeRepository extends JpaRepository<GeneralTypeEntity,Long> {

    GeneralTypeEntity findByShortCodeAndEntityCodeName(String shortCode, String entityCodeName);
}
