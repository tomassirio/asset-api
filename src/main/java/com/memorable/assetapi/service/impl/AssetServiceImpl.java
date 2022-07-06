package com.memorable.assetapi.service.impl;

import com.memorable.assetapi.exception.AssetNotFoundException;
import com.memorable.assetapi.exception.ScoreTypeException;
import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import com.memorable.assetapi.model.Score;
import com.memorable.assetapi.model.ScoreType;
import com.memorable.assetapi.repository.AssetRepository;
import com.memorable.assetapi.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Override
    public Asset findAssetById(UUID uuid) {
        Optional<Asset> assetOptional = Optional.ofNullable(assetRepository.findOne(uuid));
        if(assetOptional.isEmpty()) {
            throw new AssetNotFoundException("No existing asset with id: " + uuid);
        }
        return assetOptional.get();
    }

    @Override
    public Integer findScoreAverageByAssetTypeAndScoreType(AssetType assetType, ScoreType scoreType) {
        List<Asset> assets = assetRepository.findAssetsByAssetType(assetType);
        Integer sum = 0;
        for(Asset asset : assets) {
            Optional<Score> scoreOptional = asset.getScoreTypes().stream().filter(score -> score.getScoreType().equals(scoreType)).findFirst();
            if (scoreOptional.isEmpty()) {
                throw new ScoreTypeException("Invalid ScoreType");
            }
            sum += scoreOptional.get().getValue();
        }
        return sum / assets.size();
    }
}
