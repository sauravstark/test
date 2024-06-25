package com.example.leaderboard.model.score.record;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ScoreRecordDto {
    private Long userId;
    private Long gameId;
    private Long score;
    private LocalDateTime recordTime;
}
