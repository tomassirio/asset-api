package com.memorable.assetapi.model;

import com.memorable.assetapi.exception.ScoreTypeException;

import java.util.Arrays;
import java.util.Optional;

public enum ScoreType {
    SCORE1(1),
    SCORE2(2),
    SCORE3(3);

    private final int type;

    ScoreType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static ScoreType getScoreType(Integer type) {
        Optional<ScoreType> scoreTypeOptional = Arrays.stream(ScoreType.values()).filter(
                scoreType -> scoreType.getType() == type).findFirst();
        if (scoreTypeOptional.isEmpty()) {
            throw new ScoreTypeException("Score Type not Supported");
        }
        return scoreTypeOptional.get();
    }
}
