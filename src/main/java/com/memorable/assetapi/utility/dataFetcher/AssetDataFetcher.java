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
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AssetDataFetcher implements DataFetcher<Asset> {

    private final AssetService assetService;

    @Override
    public Asset get(DataFetchingEnvironment env) {
        Map args = env.getArguments();
        Asset asset = assetService.findAssetById(UUID.fromString((String) args.get("id")));
        return asset;

    }
}
