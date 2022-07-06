package com.memorable.assetapi.utility.dataLoader;

import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import com.memorable.assetapi.model.Score;
import com.memorable.assetapi.model.ScoreType;
import com.memorable.assetapi.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AssetDataLoader {
    private final AssetRepository assetRepository;

    @PostConstruct
    private void generateData() {
        List<Asset> assets = List.of(
                Asset.builder()
                        .id(UUID.randomUUID())
                        .name("asset1")
                        .assetType(AssetType.VIDEO)
                        .ScoreTypes(List.of(
                                Score.builder().scoreType(ScoreType.SCORE1).value(4).build(),
                                Score.builder().scoreType(ScoreType.SCORE2).value(8).build(),
                                Score.builder().scoreType(ScoreType.SCORE3).value(15).build()
                        ))
                        .build(),
                Asset.builder()
                        .id(UUID.randomUUID())
                        .name("asset2")
                        .assetType(AssetType.IMAGE)
                        .ScoreTypes(List.of(
                                Score.builder().scoreType(ScoreType.SCORE1).value(16).build(),
                                Score.builder().scoreType(ScoreType.SCORE2).value(23).build(),
                                Score.builder().scoreType(ScoreType.SCORE3).value(42).build()
                        ))
                        .build(),
                Asset.builder()
                        .id(UUID.randomUUID())
                        .name("asset3")
                        .assetType(AssetType.VIDEO)
                        .ScoreTypes(List.of(
                                Score.builder().scoreType(ScoreType.SCORE1).value(69).build(),
                                Score.builder().scoreType(ScoreType.SCORE2).value(42).build(),
                                Score.builder().scoreType(ScoreType.SCORE3).value(0).build()
                        ))
                        .build()
        );
        assetRepository.saveAll(assets);
    }
}
