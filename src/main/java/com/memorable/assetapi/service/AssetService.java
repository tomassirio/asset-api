package com.memorable.assetapi.service;


import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import com.memorable.assetapi.model.ScoreType;

import java.util.UUID;

public interface AssetService {
    Asset findAssetById(UUID uuid);
    Integer findScoreAverageByAssetTypeAndScoreType(AssetType assetType, ScoreType scoreType);
}
