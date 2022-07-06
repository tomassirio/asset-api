package com.memorable.assetapi.utility.dataFetcher;

import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import com.memorable.assetapi.model.ScoreType;
import com.memorable.assetapi.service.AssetService;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AssetByTypeAndScoreTypeDataFetcher implements DataFetcher<Integer> {

    private final AssetService assetService;

    @Override
    public Integer get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        return assetService.findScoreAverageByAssetTypeAndScoreType(
                AssetType.getAssetType((String) args.get("assetType")),
                ScoreType.getScoreType((Integer) args.get("scoreType")));

    }
}
