package com.memorable.assetapi.service;

import com.memorable.assetapi.exception.AssetNotFoundException;
import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import com.memorable.assetapi.model.ScoreType;
import com.memorable.assetapi.repository.AssetRepository;
import com.memorable.assetapi.service.impl.AssetServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
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

    @Test
    public void when_findAverage_with_assetType_image_and_scoreType1_then_return_valid_result() {
        Integer expectedResult = 1;
        when(assetRepository.findAssetsByAssetType(any())).thenReturn(List.of(createAsset(), createAsset(), createAsset()));

        Integer result = assetService.findScoreAverage(AssetType.IMAGE, ScoreType.SCORE1);

        verify(assetRepository, Mockito.times(1)).findAssetsByAssetType(any());

        assertEquals(result, expectedResult);
    }

    @Test
    public void when_findAverage_with_assetType_image_and_scoreType2_then_return_valid_result() {
        Integer expectedResult = 2;
        when(assetRepository.findAssetsByAssetType(any())).thenReturn(List.of(createAsset(), createAsset(), createAsset()));

        Integer result = assetService.findScoreAverage(AssetType.IMAGE, ScoreType.SCORE2);

        verify(assetRepository, Mockito.times(1)).findAssetsByAssetType(any());

        assertEquals(result, expectedResult);
    }

    @Test
    public void when_findAverage_with_assetType_image_and_scoreType3_then_return_valid_result() {
        Integer expectedResult = 3;
        when(assetRepository.findAssetsByAssetType(any())).thenReturn(List.of(createAsset(), createAsset(), createAsset()));

        Integer result = assetService.findScoreAverage(AssetType.IMAGE, ScoreType.SCORE3);

        verify(assetRepository, Mockito.times(1)).findAssetsByAssetType(any());

        assertEquals(result, expectedResult);
    }


}
