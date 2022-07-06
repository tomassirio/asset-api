package com.memorable.assetapi.service.impl;

import com.memorable.assetapi.exception.AssetNotFoundException;
import com.memorable.assetapi.exception.ScoreTypeException;
import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import com.memorable.assetapi.model.ScoreType;
import com.memorable.assetapi.repository.AssetRepository;
import com.memorable.assetapi.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

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
    public Integer findScoreAverageByAssetTypeAndScoreType(AssetType assetType, ScoreType scoreType) {
        List<Asset> assets = assetRepository.findAssetsByAssetType(assetType);
        Integer sum = assets.stream().map(
                        asset -> asset.getScoreTypes().stream()
                                .filter(score -> score.getScoreType().equals(scoreType))
                                .findFirst()
                                .orElseThrow(() -> new ScoreTypeException("Invalid ScoreType"))
                                .getValue())
                .reduce(0, Integer::sum);
        return sum / assets.size();
    }
}
