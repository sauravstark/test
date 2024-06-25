package com.example.leaderboard.service;

import com.example.leaderboard.model.score.record.CreateScoreRecordRequest;
import com.example.leaderboard.model.score.record.StoredScoreRecord;
import lombok.val;

import com.example.leaderboard.repository.ScoreRecordRepository;
import com.example.leaderboard.utils.Utils;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScoreRecordService {
    private static final Logger log = LoggerFactory.getLogger(ScoreRecordService.class);
    private ScoreRecordRepository scoreRecordRepository;
    private GameService gameService;
    private UserService userService;

    public List<StoredScoreRecord> getAllScoreRecords(int page, int count) {
        val pageable = Utils.pageableFromPageAndCount(page, count);
        return scoreRecordRepository.findByDeletedFalse(pageable).toList();
    }

    public List<StoredScoreRecord> getGameSortedScoreRecords(String gamename, int page, int count) {
        val optionalStoredGame = gameService.findGame(gamename);
        if (optionalStoredGame.isEmpty()) {
            return Collections.emptyList();
        }
        val gameId = optionalStoredGame.get().getId();
        val pageable = Utils.pageableFromPageAndCount(page, count);
        return scoreRecordRepository.findTopScoresByGameId(gameId, pageable);
    }

    public Optional<StoredScoreRecord> getScoreRecordByGameAndUser(String gamename, String username) {
        val optionalGameIdUserId = getGameIdAndUserId(gamename, username);
        if (optionalGameIdUserId.isEmpty()) {
            return Optional.empty();
        }
        val gameId = optionalGameIdUserId.get().getFirst();
        val userId = optionalGameIdUserId.get().getSecond();
        return scoreRecordRepository.findByGameIdAndUserIdAndDeletedFalse(gameId, userId);
    }

    public Optional<StoredScoreRecord> createScoreRecord(String gamename, String username, CreateScoreRecordRequest createScoreRecordRequest) {
        val optionalGameIdUserId = getGameIdAndUserId(gamename, username);
        if (optionalGameIdUserId.isEmpty()) {
            return Optional.empty();
        }
        val gameId = optionalGameIdUserId.get().getFirst();
        val userId = optionalGameIdUserId.get().getSecond();
        val optionalStoredScoreRecord = scoreRecordRepository.findByGameIdAndUserId(gameId, userId);
        if (optionalStoredScoreRecord.isPresent()) {
            return optionalStoredScoreRecord.map(storedScoreRecord -> {
                storedScoreRecord.setScore(createScoreRecordRequest.getScore());
                storedScoreRecord.setRecordTime(createScoreRecordRequest.getRecordTime());
                storedScoreRecord.setDeleted(false);
                storedScoreRecord.setUpdateTime(LocalDateTime.now());
                return scoreRecordRepository.save(storedScoreRecord);
            });
        }
        val storedScoreRecord = StoredScoreRecord.builder()
                .gameId(gameId)
                .userId(userId)
                .score(createScoreRecordRequest.getScore())
                .recordTime(createScoreRecordRequest.getRecordTime())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
        return Optional.of(scoreRecordRepository.save(storedScoreRecord));
    }

    public Optional<StoredScoreRecord> deleteScoreRecord(String gamename, String username) {
        val optionalGameIdUserId = getGameIdAndUserId(gamename, username);
        if (optionalGameIdUserId.isEmpty()) {
            return Optional.empty();
        }
        val gameId = optionalGameIdUserId.get().getFirst();
        val userId = optionalGameIdUserId.get().getSecond();
        return scoreRecordRepository.findByGameIdAndUserIdAndDeletedFalse(gameId, userId)
                .map(storedScoreRecord -> {
                    storedScoreRecord.setDeleted(true);
                    storedScoreRecord.setUpdateTime(LocalDateTime.now());
                    return scoreRecordRepository.save(storedScoreRecord);
                });
    }

    private Optional<Pair<Long, Long>> getGameIdAndUserId(String gamename, String username) {
        val optionalStoredGame = gameService.findGame(gamename);
        if (optionalStoredGame.isEmpty()) {
            log.error("Game: {} not found while creating a record", gamename);
            return Optional.empty();
        }

        val optionalStoredUser = userService.findUser(username);
        if (optionalStoredUser.isEmpty()) {
            log.error("User: {} not found while creating a record", username);
            return Optional.empty();
        }

        val gameId = optionalStoredGame.get().getId();
        val userId = optionalStoredUser.get().getId();
        return Optional.of(Pair.of(gameId, userId));
    }
}
