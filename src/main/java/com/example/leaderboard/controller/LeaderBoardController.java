package com.example.leaderboard.controller;

import com.example.leaderboard.model.leaderboard.GameHighScoresDto;
import com.example.leaderboard.model.leaderboard.UserScoreDto;
import com.example.leaderboard.model.score.record.StoredScoreRecord;
import com.example.leaderboard.service.GameService;
import com.example.leaderboard.service.ScoreRecordService;
import com.example.leaderboard.service.UserService;
import com.example.leaderboard.utils.Utils;
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
        val optionalStoredGame = gameService.findGame(gamename);
        if (optionalStoredGame.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        val storedScoreRecordList = scoreRecordService.getGameSortedScoreRecords(gamename, 0, 5);
        val gameDto = Utils.gameDtoFromStoredGame(optionalStoredGame.get());
        val gameHighScoresDto = GameHighScoresDto.builder()
                .game(gameDto)
                .userScores(storedScoreRecordList.stream()
                        .map(storedScoreRecord -> {
                            val optionalStoredUser = userService.findUserById(storedScoreRecord.getUserId());
                            if (optionalStoredUser.isEmpty()) {
                                log.error("User Mapping for id: {} not found", storedScoreRecord.getUserId());
                                throw new RuntimeException("User Mapping not found");
                            }
                            val storedUser = optionalStoredUser.get();
                            return UserScoreDto.builder()
                                    .username(storedUser.getUsername())
                                    .name(storedUser.getName())
                                    .email(storedUser.getEmail())
                                    .dob(storedUser.getDob())
                                    .score(storedScoreRecord.getScore())
                                    .recordTime(storedScoreRecord.getRecordTime())
                                    .build();
                        })
                        .toList())
                .build();
        return ResponseEntity.ok(gameHighScoresDto);
    }
}
