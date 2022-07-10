package com.memorable.assetapi.model.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AssetRequest {
    private String name;
    private String assetType;
    private List<Integer> scores;
}
