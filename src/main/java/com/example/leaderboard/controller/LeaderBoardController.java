package com.example.leaderboard.controller;

import com.example.leaderboard.model.game.GameDto;
import com.example.leaderboard.model.leaderboard.GameHighScoresDto;
import com.example.leaderboard.model.leaderboard.UserScoreDto;
import com.example.leaderboard.model.score.record.ScoreRecordEntity;
import com.example.leaderboard.model.user.UserEntity;
import com.example.leaderboard.service.GameService;
import com.example.leaderboard.service.ScoreRecordService;
import com.example.leaderboard.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/leaderboard")
@AllArgsConstructor
public class LeaderBoardController {
    private UserService userService;
    private GameService gameService;
    private ScoreRecordService scoreRecordService;

    @GetMapping("/{gamename}")
    public ResponseEntity<GameHighScoresDto> getGameHighScores(@PathVariable String gamename) {
        val gameDto = gameDtoFromGamename(gamename);
        val userScoreDtoList = scoreRecordService.findTopScoreRecordsByGamename(gamename, 0, 5).stream()
                .map(this::userScoreDtoFromScoreEntity)
                .toList();
        val gameHighScoresDto = GameHighScoresDto.builder()
                .game(gameDto)
                .userScores(userScoreDtoList)
                .build();
        return ResponseEntity.ok(gameHighScoresDto);
    }

    private GameDto gameDtoFromGamename(String gamename) {
        val optionalGameEntity = gameService.findGameByName(gamename);
        if (optionalGameEntity.isEmpty()) {
            log.error("Game: {} not found", gamename);
        }

        return optionalGameEntity.map(GameDto::fromGameEntity)
                .orElse(GameDto.builder()
                        .name("deleted-game")
                        .displayName("Deleted Game")
                        .description("This Game is deleted.")
                        .build());
    }

    private UserScoreDto userScoreDtoFromScoreEntity(ScoreRecordEntity scoreRecordEntity) {
        val optionalUserEntity = userService.findUserByUsername(scoreRecordEntity.getUsername());
        if (optionalUserEntity.isEmpty()) {
            log.error("User: {} not found", scoreRecordEntity.getUsername());
        }

        return UserScoreDto.builder()
                .username(optionalUserEntity.map(UserEntity::getUsername).orElse("deleted-user"))
                .name(optionalUserEntity.map(UserEntity::getName).orElse("Deleted User"))
                .email(optionalUserEntity.map(UserEntity::getEmail).orElse(null))
                .dob(optionalUserEntity.map(UserEntity::getDob).orElse(null))
                .score(scoreRecordEntity.getScore())
                .recordTime(scoreRecordEntity.getRecordTime())
                .build();
    }
}
