package com.example.leaderboard.controller;

import com.example.leaderboard.model.score.record.CreateScoreRecordRequest;
import com.example.leaderboard.model.score.record.ScoreRecordDto;
import com.example.leaderboard.model.score.record.StoredScoreRecord;
import com.example.leaderboard.service.ScoreRecordService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
@AllArgsConstructor
public class ScoreRecordController {
    private ScoreRecordService scoreRecordService;

    @GetMapping
    public ResponseEntity<List<ScoreRecordDto>> getAllScoreRecords(@RequestParam int page,
                                                                   @RequestParam int count) {
        val scoreRecordDtoList = scoreRecordService.getAllScoreRecords(page, count).stream()
                .map(ScoreRecordController::scoreRecordDtoFromStoredScoreRecord)
                .toList();
        return ResponseEntity.ok(scoreRecordDtoList);
    }

    @GetMapping("/{gamename}/{username}")
    public ResponseEntity<ScoreRecordDto> getScoreRecordByGameAndUser(String gamename, String username) {
        val scoreRecordDto = scoreRecordService.getScoreRecordByGameAndUser(gamename, username)
                .map(ScoreRecordController::scoreRecordDtoFromStoredScoreRecord);
        return ResponseEntity.of(scoreRecordDto);
    }

    @PostMapping("/{gamename}/{username}")
    public ResponseEntity<ScoreRecordDto> createScoreRecord(@PathVariable String gamename,
                                                            @PathVariable String username,
                                                            @RequestBody CreateScoreRecordRequest createScoreRecordRequest) {
        val optionalScoreRecordDto = scoreRecordService.createScoreRecord(gamename, username, createScoreRecordRequest)
                .map(ScoreRecordController::scoreRecordDtoFromStoredScoreRecord);
        return ResponseEntity.of(optionalScoreRecordDto);
    }

    @DeleteMapping("/{gamename}/{username}")
    public ResponseEntity<Void> deleteScoreRecord(@PathVariable String gamename,
                                                  @PathVariable String username) {
        val optionalStoredScoreRecord = scoreRecordService.deleteScoreRecord(gamename, username);
        if (optionalStoredScoreRecord.isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    private static ScoreRecordDto scoreRecordDtoFromStoredScoreRecord(StoredScoreRecord storedScoreRecord) {
        return ScoreRecordDto.builder()
                .gameId(storedScoreRecord.getGameId())
                .userId(storedScoreRecord.getUserId())
                .score(storedScoreRecord.getScore())
                .recordTime(storedScoreRecord.getRecordTime())
                .build();
    }
}
