package com.memorable.assetapi.repository;

import com.memorable.assetapi.model.Asset;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssetRepository extends PagingAndSortingRepository<Asset, UUID> {}
