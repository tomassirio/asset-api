package com.memorable.assetapi.repository;

import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AssetRepository extends MongoRepository<Asset, UUID> {
//    @Query("{asset: { $assetType: ?0 } })")
    List<Asset> findAssetsByAssetType(AssetType assetType);
}
