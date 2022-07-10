package com.memorable.assetapi.validator;

import com.memorable.assetapi.model.Asset;
import com.memorable.assetapi.model.AssetType;
import com.memorable.assetapi.model.Score;
import com.memorable.assetapi.model.ScoreType;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
public class AssetValidator implements Validator {

    /**
     * This Validator validates *just* Asset instances
     */
    @Override
    public boolean supports(@NotNull Class clazz) {
        return Asset.class.equals(clazz);
    }

    @Override
    public void validate(@NotNull Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        ValidationUtils.rejectIfEmpty(e, "scores", "scores.empty");
        ValidationUtils.rejectIfEmpty(e, "assetType", "assetType.empty");

        Asset asset = (Asset) obj;
        for (Score score : asset.getScores()) {
            if(score.getValue() < 0 || score.getValue() > 100) {
                e.rejectValue("scores", "score.invalid-value");
            }
        }
        if(asset.getScores().size() != ScoreType.values().length) {
            e.rejectValue("scores", "score.score-count-invalid");
        }
    }
}