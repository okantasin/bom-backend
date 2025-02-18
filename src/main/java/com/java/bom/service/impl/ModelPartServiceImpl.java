package com.java.bom.service.impl;

import com.java.bom.dto.ModelPartProductionResult;
import com.java.bom.dto.ModelPartResponse;
import com.java.bom.dto.PartProductionDetail;
import com.java.bom.dto.PartResponse;
import com.java.bom.entity.*;
import com.java.bom.entity.common.GeneralStatusEntity;
import com.java.bom.repository.*;
import com.java.bom.repository.common.GeneralStatusRepository;
import com.java.bom.service.ActionLogService;
import com.java.bom.service.ModelPartService;
import com.java.bom.service.ModelService;
import com.java.bom.service.PartService;
import com.java.bom.utils.PlanningType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private ModelPartConfigRepository modelPartConfigRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelPercentageRepository modelPercentageRepository;

    @Override
    public ModelPart addPartToModel(Long modelId, Long partId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Parça miktarı sıfırdan büyük olmalıdır.");
        }

        Model model = modelService.getModelById(modelId);
        if (model == null) {
            throw new RuntimeException("Model bulunamadı: " + modelId);
        }

        Part part = partService.getPartById(partId);
        if (part == null) {
            throw new RuntimeException("Parça bulunamadı: " + partId);
        }

        // Model ve parçaya özel requiredQuantity değerini `model_part_config` tablosundan çekelim.
        Optional<ModelPartConfig> config = modelPartConfigRepository.findByModelIdAndPartId(modelId, partId);

        if (config.isEmpty()) {
            throw new RuntimeException("Bu model için gerekli parça miktarı tanımlanmamış.");
        }

        int requiredQuantity = config.get().getRequiredQuantity();

        if (quantity < requiredQuantity) {
            throw new RuntimeException("Bu model için minimum " + requiredQuantity + " adet " + part.getName() + " eklenmelidir.");
        }

        ModelPart modelPart = new ModelPart();
        modelPart.setModel(model);
        modelPart.setPart(part);
        modelPart.setQuantity(quantity);

        GeneralStatusEntity defaultStatus = generalStatusRepository.findByShortCode("ACTIVE");
        if (Objects.isNull(defaultStatus)) {
            throw new RuntimeException("ACTIVE status not found");
        }

        modelPart.setStatusId(defaultStatus.getId());
        modelPartRepository.save(modelPart);
        actionLogService.logAction("ModelPart", modelPart.getId(), "ADD", "Added part with quantity " + quantity + ", required: " + requiredQuantity);
        return modelPart;
    }

    @Override
    public ModelPartResponse getPartsByModel(Long modelId) {
        return null;
    }

    @Override
    public ModelPartResponse getPartsByModel(Long projectId, String modelNumber) {
       Optional<Model> model =  modelRepository.findModelByModelNumberAndProject_Id(modelNumber,projectId);
           List<ModelPart> modelParts = modelPartRepository.findModelPartByModel_Id(model.get().getId());
           List<PartResponse> parts = modelParts.stream()
                   .map(mp -> new PartResponse(mp.getPart().getId(), mp.getPart().getName(), mp.getQuantity()))
                   .toList();
        actionLogService.logAction("ModelPart",
                model.get().getId(),
                "getPartsByModel",
                "");

        return new ModelPartResponse(model.get().getId(), model.get().getName(), parts);

    }

    @Transactional
    @Override
    public ModelPart softDeletePartFromModel(Long modelId, Long partId) {
        ModelPart modelPart = modelPartRepository.inquireModelPartByPart_IdAndModel_Id(modelId, partId)
                .orElseThrow(() -> new RuntimeException("Bu modele ait parça bulunamadı: Model ID = " + modelId + ", Part ID = " + partId));


        GeneralStatusEntity defaultStatus = generalStatusRepository.findByShortCode("INACTIVE");
        modelPart.setStatusId(defaultStatus.getId());
        actionLogService.logAction("ModelPart",
                modelPart.getModel().getId(),
                modelPart.getModel().getName(),
                 "Silindi");

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
                        "silindi (Part ID: " + partId + ")");
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

    @Override
    public ModelPart getPercentance(Long modelId, Long partId) {
        return null;
    }

    public List<ModelPartProductionResult> calculatePartProduction(Long projectId, int totalProduction, int year, Integer month, Integer week) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Proje bulunamadı: " + projectId));

        List<ModelPercentage> percentages;

        // Planlama tipine göre uygun model yüzdelerini çekiyoruz
        if (project.getPlanningType() == PlanningType.SABIT) {
            percentages = modelPercentageRepository.findByProjectIdAndPlanningTypeAndActive(projectId, PlanningType.SABIT, "ACTIVE");
        } else if (project.getPlanningType() == PlanningType.AYLIK && month != null) {
            percentages = modelPercentageRepository.findByProjectIdAndPlanningTypeAndYearAndMonth(projectId, PlanningType.AYLIK, year, month, "ACTIVE");
        } else if (project.getPlanningType() == PlanningType.HAFTALIK && week != null) {
            percentages = modelPercentageRepository.findByProjectIdAndPlanningTypeAndYearAndWeek(projectId, PlanningType.HAFTALIK, year, week, "ACTIVE");
        } else {
            throw new IllegalArgumentException("Geçersiz planlama tipi veya eksik parametreler!");
        }

        // Model bazında üretim adetlerini hesapla
        Map<Model, Integer> modelProductionCounts = new HashMap<>();
        for (ModelPercentage mp : percentages) {
            int modelCount = (int) (totalProduction * (mp.getPercentage() / 100.0));
            modelProductionCounts.put(mp.getModel(), modelCount);
        }

        // Model bazında gerekli parçaları hesapla
        List<ModelPartProductionResult> modelPartProductionResults = new ArrayList<>();
        Map<Long, Integer> totalPartCounts = new HashMap<>();

        for (Map.Entry<Model, Integer> entry : modelProductionCounts.entrySet()) {
            Model model = entry.getKey();
            int modelCount = entry.getValue();

            List<ModelPart> modelParts = modelPartRepository.getModelPartByModel_Id(model.getId());
            List<PartProductionDetail> partDetails = new ArrayList<>();

            for (ModelPart modelPart : modelParts) {
                int requiredQuantity = modelPart.getQuantity();
                int totalPartCount = modelCount * requiredQuantity;

                // Model bazında parçaların listesini oluştur
                partDetails.add(new PartProductionDetail(
                        modelPart.getPart().getId(),
                        modelPart.getPart().getName(),
                        requiredQuantity,
                        totalPartCount
                ));

                // Genel parça üretim adetlerini hesapla
                totalPartCounts.merge(modelPart.getPart().getId(), totalPartCount, Integer::sum);
            }

            // Her model için üretilecek parçaları listeye ekle
            modelPartProductionResults.add(new ModelPartProductionResult(
                    model.getId(),
                    model.getName(),
                    modelCount,
                    partDetails
            ));
        }

        // Toplam tüm parçaların kaç adet üretileceğini içeren sonucu döndürüyoruz
        modelPartProductionResults.add(new ModelPartProductionResult( "Toplam Üretim", totalProduction,
                totalPartCounts.entrySet().stream()
                        .map(entry -> new PartProductionDetail(entry.getKey(), "", 0, entry.getValue()))
                        .collect(Collectors.toList())
        ));

        return modelPartProductionResults;
    }

}
