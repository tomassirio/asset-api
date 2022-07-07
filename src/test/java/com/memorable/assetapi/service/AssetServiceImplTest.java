package com.memorable.assetapi.service;

import com.memorable.assetapi.exception.AssetNotFoundException;
import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.repository.AssetRepository;
import com.memorable.assetapi.service.impl.AssetServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static com.memorable.assetapi.utils.TestUtils.createAsset;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssetServiceImplTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetServiceImpl assetService;

    @Test
    public void when_findAssetById_with_valid_uuid_then_return_valid_asset() {
        Asset expectedResult = createAsset();
        when(assetRepository.findById(any())).thenReturn(Optional.of(expectedResult));

        Asset result = assetService.findAssetById(UUID.randomUUID());

        verify(assetRepository, Mockito.times(1)).findById(any());

        assertEquals(result.getId(), expectedResult.getId());
    }

    @Test(expected = AssetNotFoundException.class)
    public void when_findAssetById_with_invalid_uuid_then_throw_exception() {
        assetService.findAssetById(UUID.randomUUID());

        verify(assetRepository.findById(any()), Mockito.atLeastOnce());
    }
//
//    @Test
//    public void when_

}
