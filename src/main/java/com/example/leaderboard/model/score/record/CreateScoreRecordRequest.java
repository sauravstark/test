package com.example.leaderboard.model.score.record;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateScoreRecordRequest {
    private Long score;
    private LocalDateTime recordTime;
}
