package com.example.leaderboard.model.score.record;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ScoreRecordDto {
    private String username;
    private String gamename;
    private Long score;
    private LocalDateTime recordTime;

    public static ScoreRecordDto fromScoreRecordEntity(ScoreRecordEntity scoreRecordEntity) {
        return builder()
                .gamename(scoreRecordEntity.getGamename())
                .username(scoreRecordEntity.getUsername())
                .score(scoreRecordEntity.getScore())
                .recordTime(scoreRecordEntity.getRecordTime())
                .build();
    }
}
