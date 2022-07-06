package com.memorable.assetapi.model;

import com.memorable.assetapi.exception.AssetTypeException;
import com.memorable.assetapi.exception.ScoreTypeException;

import java.util.Arrays;
import java.util.Optional;

public enum AssetType {
    IMAGE("image"),
    VIDEO("video");

    private final String type;

    AssetType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static AssetType getAssetType(String type) {
        Optional<AssetType> assetTypeOptional = Arrays.stream(AssetType.values()).filter(
                assetType -> assetType.getType().equals(type)).findFirst();
        if (assetTypeOptional.isEmpty()) {
            throw new AssetTypeException("Asset Type not Supported");
        }
        return assetTypeOptional.get();
    }
}
