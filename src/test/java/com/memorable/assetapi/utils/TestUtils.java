package com.memorable.assetapi.utils;

import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import com.memorable.assetapi.model.Score;
import com.memorable.assetapi.model.ScoreType;

import java.util.List;
import java.util.UUID;

public class TestUtils {

    public static Asset createAsset() {
        return Asset.builder()
                .id(UUID.fromString("0000325e-b7ca-4f6d-8b76-08464d993610"))
                .name("asset")
                .assetType(AssetType.IMAGE)
                .scores(
                        List.of(
                                Score.builder().scoreType(ScoreType.SCORE1).value(1).build(),
                                Score.builder().scoreType(ScoreType.SCORE2).value(2).build(),
                                Score.builder().scoreType(ScoreType.SCORE3).value(3).build()
                        )
                )
                .build();
    }
}
