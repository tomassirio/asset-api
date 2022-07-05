package com.memorable.assetapi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Score {
    private ScoreType scoreType;
    private Integer value;
}
