package com.example.leaderboard.model.score.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
