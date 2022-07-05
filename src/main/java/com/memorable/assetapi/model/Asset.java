package com.memorable.assetapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "assets")
public class Asset {

    private UUID id;
    private AssetType assetType;
    private String name;
    List<Score> ScoreTypes;
}
