package com.java.bom.service.impl;

import com.java.bom.dto.ModelPartResponse;
import com.java.bom.dto.PartResponse;
import com.java.bom.entity.Model;
import com.java.bom.entity.ModelPart;
import com.java.bom.entity.Part;
import com.java.bom.entity.common.GeneralStatusEntity;
import com.java.bom.repository.ModelPartRepository;
import com.java.bom.repository.common.GeneralStatusRepository;
import com.java.bom.service.ActionLogService;
import com.java.bom.service.ModelPartService;
import com.java.bom.service.ModelService;
import com.java.bom.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ModelPartServiceImpl implements ModelPartService {
    @Autowired
    private ModelPartRepository modelPartRepository;
    @Autowired
    private PartService partService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private ActionLogService actionLogService;
    @Autowired
    private GeneralStatusRepository generalStatusRepository;


    @Override
    public ModelPart addPartToModel(Long modelId, Long partId, int quantity) {
           ModelPart modelPart = new ModelPart();
           Part part= partService.getPartById(partId);
           if(part!=null){
               modelPart.setPart(part);
           }
           Model model = modelService.getModelById(modelId);
           if(model!=null){
               modelPart.setModel(model);
           }
            modelPart.setQuantity(quantity);
        GeneralStatusEntity defaultStatus = generalStatusRepository.findByShortCode("ACTIVE");
        if(Objects.isNull(defaultStatus)){
            throw new RuntimeException("ACTIVE status not found");
        }
            modelPart.setStatusId(defaultStatus.getId());
            modelPartRepository.save(modelPart);
            actionLogService.logAction("ModelPart", modelPart.getId(), "ADD", "Added part with quantity " + quantity);
            return modelPart;

    }

    @Override
    public ModelPartResponse getPartsByModel(Long modelId) {
        Model model = modelService.getModelById(modelId);
        if(model==null){
            throw new RuntimeException("Model not found");
        }

         List<ModelPart> modelParts = modelPartRepository.findModelPartByModel_Id(modelId);
         List<PartResponse> parts = modelParts.stream()
                    .map(mp -> new PartResponse(mp.getPart().getId(), mp.getPart().getName(), mp.getQuantity()))
                    .toList();
         return new ModelPartResponse(model.getId(), model.getName(), parts);
    }

    @Transactional
    @Override
    public ModelPart softDeletePartFromModel(Long modelId, Long partId) {
        ModelPart modelPart = modelPartRepository.inquireModelPartByPart_IdAndModel_Id(modelId, partId)
                .orElseThrow(() -> new RuntimeException("Bu modele ait parça bulunamadı: Model ID = " + modelId + ", Part ID = " + partId));


        GeneralStatusEntity defaultStatus = generalStatusRepository.findByShortCode("INACTIVE");
        modelPart.setStatusId(defaultStatus.getId());
        return modelPartRepository.save(modelPart);
    }

    @Override
    public ModelPart updatePartInModel(Long modelId, Long partId, int requiredQuantity) {
        if (requiredQuantity < 0) {
            throw new IllegalArgumentException("Required quantity cannot be negative.");
        }

        ModelPart modelPart = modelPartRepository.inquireModelPartByPart_IdAndModel_Id(modelId, partId)
                .orElse(null);

        if (requiredQuantity == 0) {
            // Eğer bu parçanın hiç olmaması gerekiyorsa ve sistemde varsa, silme işlemi yapabiliriz.
            if (modelPart != null) {
                modelPartRepository.delete(modelPart);
                actionLogService.logAction("ModelPart", modelPart.getId(), "DELETE",
                        "Removed part from model (Part ID: " + partId + ")");
            }
            return null;
        }

        if (requiredQuantity > 0) {
            // Eğer modelde bu parça yoksa, sadece tam olarak gerekli sayıda eklenmesine izin ver.
            if (modelPart == null) {
                return addPartToModel(modelId, partId, requiredQuantity);
            }

            int currentQuantity = modelPart.getQuantity();

            // Eğer mevcut miktar ile gerekli miktar eşleşmiyorsa güncelle.
            if (currentQuantity != requiredQuantity) {
                modelPart.setQuantity(requiredQuantity);
                actionLogService.logAction("ModelPart", modelPart.getId(), "UPDATE",
                        "Updated quantity from " + currentQuantity + " to " + requiredQuantity);
                return modelPartRepository.save(modelPart);
            }
        }

        return modelPart;
    }
}
