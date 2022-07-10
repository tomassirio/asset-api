package com.memorable.assetapi.utility.dataFetcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.service.AssetService;
import com.memorable.assetapi.model.request.AssetRequest;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CreateAssetDataFetcher implements DataFetcher<Asset> {

    private final AssetService assetService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Asset get(DataFetchingEnvironment env) {
        Map args = env.getArguments();

        return assetService.createAsset(objectMapper.convertValue(args, AssetRequest.class));

    }
}
