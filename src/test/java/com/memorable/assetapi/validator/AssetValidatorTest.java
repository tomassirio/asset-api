package com.memorable.assetapi.validator;

import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.Score;
import com.memorable.assetapi.model.ScoreType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;

import java.util.Collections;
import java.util.List;

import static com.memorable.assetapi.utils.TestUtils.createAsset;

@RunWith(SpringJUnit4ClassRunner.class)
public class AssetValidatorTest {

    @Test
    public void test_validation_with_valid_asset() throws Exception {
        Asset asset = createAsset();
        // Initialise the variables here.

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(asset, "asset");
        ValidationUtils.invokeValidator(new AssetValidator(), asset, result);

        // If errors are expected.
        Assert.assertTrue(result.getAllErrors().isEmpty());
    }

    @Test
    public void test_validation_when_score_is_higher_than_100() throws Exception {
        Asset asset = createAsset();
        // Initialise the variables here.
        asset.setScores(List.of(Score.builder().scoreType(ScoreType.SCORE1).value(101).build()));

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(asset, "asset");
        ValidationUtils.invokeValidator(new AssetValidator(), asset, result);

        // If errors are expected.
        Assert.assertFalse(result.getAllErrors().isEmpty());
    }

    @Test
    public void test_validation_when_score_is_higher_lower_than_0() throws Exception {
        Asset asset = createAsset();
        // Initialise the variables here.
        asset.setScores(List.of(Score.builder().scoreType(ScoreType.SCORE1).value(-1).build()));

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(asset, "asset");
        ValidationUtils.invokeValidator(new AssetValidator(), asset, result);

        // If errors are expected.
        Assert.assertFalse(result.getAllErrors().isEmpty());
    }

    @Test
    public void test_validation_when_there_are_no_scores() throws Exception {
        Asset asset = createAsset();
        // Initialise the variables here.
        asset.setScores(Collections.emptyList());

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(asset, "asset");
        ValidationUtils.invokeValidator(new AssetValidator(), asset, result);

        // If errors are expected.
        Assert.assertFalse(result.getAllErrors().isEmpty());
    }

    @Test
    public void test_validation_when_there_is_no_name() throws Exception {
        Asset asset = createAsset();
        // Initialise the variables here.
        asset.setName(null);

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(asset, "asset");
        ValidationUtils.invokeValidator(new AssetValidator(), asset, result);

        // If errors are expected.
        Assert.assertFalse(result.getAllErrors().isEmpty());
    }

    @Test
    public void test_validation_when_there_is_no_assetType() throws Exception {
        Asset asset = createAsset();
        // Initialise the variables here.
        asset.setAssetType(null);

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(asset, "asset");
        ValidationUtils.invokeValidator(new AssetValidator(), asset, result);

        // If errors are expected.
        Assert.assertFalse(result.getAllErrors().isEmpty());
    }

    @Test
    public void test_validation_when_there_is_less_scores() throws Exception {
        Asset asset = createAsset();
        // Initialise the variables here.
        asset.setScores(List.of(Score.builder().scoreType(ScoreType.SCORE1).value(1).build(),
                Score.builder().scoreType(ScoreType.SCORE2).value(2).build()));

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(asset, "asset");
        ValidationUtils.invokeValidator(new AssetValidator(), asset, result);

        // If errors are expected.
        Assert.assertFalse(result.getAllErrors().isEmpty());
    }

}