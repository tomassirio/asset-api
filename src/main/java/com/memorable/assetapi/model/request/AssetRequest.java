package com.memorable.assetapi.model.request;

import lombok.Data;

import java.util.List;

@Data
public class AssetRequest {
    private String name;
    private String assetType;
    private List<Integer> scores;
}
