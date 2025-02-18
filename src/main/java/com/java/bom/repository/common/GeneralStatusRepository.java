package com.java.bom.repository.common;

import com.java.bom.entity.common.GeneralStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralStatusRepository extends JpaRepository<GeneralStatusEntity,Long> {
    GeneralStatusEntity findByShortCode(String shortCode);
}
