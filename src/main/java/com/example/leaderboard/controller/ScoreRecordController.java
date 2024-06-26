package com.example.leaderboard.controller;

import com.example.leaderboard.model.score.record.CreateScoreRecordRequest;
import com.example.leaderboard.model.score.record.ScoreRecordDto;
import com.example.leaderboard.service.ScoreRecordService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/records")
@AllArgsConstructor
public class ScoreRecordController {
    private ScoreRecordService scoreRecordService;

    @GetMapping
    public ResponseEntity<List<ScoreRecordDto>> getAllScoreRecords(@RequestParam int page,
                                                                   @RequestParam int count) {
        val scoreRecordDtoList = scoreRecordService.findScoreRecordsByPage(page, count).stream()
                .map(ScoreRecordDto::fromScoreRecordEntity)
                .toList();
        return ResponseEntity.ok(scoreRecordDtoList);
    }

    @GetMapping("/{gamename}/{username}")
    public ResponseEntity<ScoreRecordDto> getScoreRecordByGameAndUser(String gamename, String username) {
        val scoreRecordDto = scoreRecordService.findScoreRecordByGamenameAndUsername(gamename, username)
                .map(ScoreRecordDto::fromScoreRecordEntity);
        return ResponseEntity.of(scoreRecordDto);
    }

    @PostMapping("/{gamename}/{username}")
    public ResponseEntity<ScoreRecordDto> createScoreRecord(@PathVariable String gamename,
                                                            @PathVariable String username,
                                                            @RequestBody CreateScoreRecordRequest createScoreRecordRequest) {
        val optionalScoreRecordDto = scoreRecordService.createScoreRecord(gamename, username, createScoreRecordRequest)
                .map(ScoreRecordDto::fromScoreRecordEntity);
        return ResponseEntity.of(optionalScoreRecordDto);
    }

    @DeleteMapping("/{gamename}/{username}")
    public ResponseEntity<Void> deleteScoreRecord(@PathVariable String gamename,
                                                  @PathVariable String username) {
        val optionalScoreRecordEntity = scoreRecordService.deleteScoreRecord(gamename, username);
        if (optionalScoreRecordEntity.isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
