package com.java.bom.service.impl;

import com.java.bom.entity.ActionLog;
import com.java.bom.repository.ActionLogRepository;
import com.java.bom.service.ActionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActionLogServiceImpl  implements ActionLogService {

    @Autowired
    private ActionLogRepository actionLogRepository;

    public void logAction(String entityName, Long entityId, String action, String details) {
        ActionLog log = new ActionLog();
        log.setEntityName(entityName);
        log.setEntityId(entityId);
        log.setAction(action);
        log.setDetails(details);
        log.setTimestamp(LocalDateTime.now());
        actionLogRepository.save(log);
    }
}
