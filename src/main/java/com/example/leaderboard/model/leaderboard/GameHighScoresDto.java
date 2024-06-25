package com.example.leaderboard.model.leaderboard;

import com.example.leaderboard.model.game.GameDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GameHighScoresDto {
    private GameDto game;
    private List<UserScoreDto> userScores;
}
