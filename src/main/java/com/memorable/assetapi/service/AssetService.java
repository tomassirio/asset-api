package com.memorable.assetapi.service;


import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import com.memorable.assetapi.model.ScoreType;
import com.memorable.assetapi.model.request.AssetRequest;

import java.util.UUID;

public interface AssetService {
    Asset createAsset(AssetRequest request);
    Asset findAssetById(UUID uuid);
    Integer findScoreAverage(AssetType assetType, ScoreType scoreType);
}
