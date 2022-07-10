package com.memorable.assetapi.service.impl;

import com.memorable.assetapi.exception.AssetNotFoundException;
import com.memorable.assetapi.exception.AssetValidationException;
import com.memorable.assetapi.exception.ScoreTypeException;
import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import com.memorable.assetapi.model.Score;
import com.memorable.assetapi.model.ScoreType;
import com.memorable.assetapi.repository.AssetRepository;
import com.memorable.assetapi.service.AssetService;
import com.memorable.assetapi.model.request.AssetRequest;
import com.memorable.assetapi.validator.AssetValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Override
    public Asset createAsset(AssetRequest request) {
        Asset newAsset = Asset.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .assetType(AssetType.getAssetType(request.getAssetType()))
                .scores(request.getScores().stream().map(
                        score -> Score.builder()
                                .value(score)
                                .scoreType(ScoreType.getScoreType(score))
                                .build())
                        .collect(Collectors.toList()))
                .build();
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(newAsset, "asset");
        ValidationUtils.invokeValidator(new AssetValidator(), newAsset, result);
        if (result.getAllErrors().size() > 0) {
            result.getAllErrors().forEach(e -> log.error(e.getObjectName()));
           throw new AssetValidationException("Invalid asset request");
        }
        return assetRepository.save(newAsset);
    }

    /**
     * findAssetById
     * @param uuid
     * @return asset with id == uuid
     */
    @Override
    public Asset findAssetById(UUID uuid) {
        Optional<Asset> assetOptional = assetRepository.findById(uuid);
        if (assetOptional.isEmpty()) {
            throw new AssetNotFoundException("No existing asset with id: " + uuid);
        }
        return assetOptional.get();
    }

    /**
     * findScoreAverageByAssetTypeAndScoreType
     * @param assetType
     * @param scoreType
     * @return the average obtained from scoreType == scoreType and assetType == assetType
     */
    @Override
    public Integer findScoreAverage(AssetType assetType, ScoreType scoreType) {
        List<Asset> assets = assetRepository.findAssetsByAssetType(assetType);
        Integer sum = assets.stream().map(
                        asset -> asset.getScores().stream()
                                .filter(score -> score.getScoreType().equals(scoreType))
                                .findFirst()
                                .orElseThrow(() -> new ScoreTypeException("Invalid ScoreType"))
                                .getValue())
                .reduce(0, Integer::sum);
        return sum / assets.size();
    }
}
