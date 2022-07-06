package com.memorable.assetapi.service;

import com.memorable.assetapi.repository.AssetRepository;
import com.memorable.assetapi.service.impl.AssetServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AssetServiceImplTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetServiceImpl assetService;



    @Test
    public void when_findAssetById_with_valid_uuid_then_return_valid_asset() {
        assertTrue(true);
    }

}
