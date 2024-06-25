package com.example.leaderboard.model.score.record;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateScoreRecordRequest {
    private Long score;
    private LocalDateTime recordTime;
}
