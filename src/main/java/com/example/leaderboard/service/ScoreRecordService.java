package com.example.leaderboard.service;

import com.example.leaderboard.model.score.record.CreateScoreRecordRequest;
import com.example.leaderboard.model.score.record.ScoreRecordEntity;

import java.util.List;
import java.util.Optional;

public interface ScoreRecordService {
    List<ScoreRecordEntity> findScoreRecordsByPage(int page, int count);
    List<ScoreRecordEntity> findTopScoreRecordsByGamename(String gamename, int page, int count);
    Optional<ScoreRecordEntity> findScoreRecordByGamenameAndUsername(String gamename, String username);
    Optional<ScoreRecordEntity> createScoreRecord(String gamename, String username, CreateScoreRecordRequest createScoreRecordRequest);
    Optional<ScoreRecordEntity> deleteScoreRecord(String gamename, String username);
}
