package com.example.leaderboard.service;

import com.example.leaderboard.model.score.record.CreateScoreRecordRequest;
import com.example.leaderboard.model.score.record.ScoreRecordEntity;
import com.example.leaderboard.repository.ScoreRecordRepository;
import com.example.leaderboard.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ScoreRecordServiceImpl implements ScoreRecordService {
    private ScoreRecordRepository scoreRecordRepository;
    private GameService gameService;
    private UserService userService;

    public List<ScoreRecordEntity> findScoreRecordsByPage(int page, int count) {
        val pageable = Utils.pageableFromPageAndCount(page, count);
        return scoreRecordRepository.findByDeletedFalse(pageable).toList();
    }

    public List<ScoreRecordEntity> findTopScoreRecordsByGamename(String gamename, int page, int count) {
        val pageable = Utils.pageableFromPageAndCount(page, count);
        return scoreRecordRepository.findTopScoresByGamename(gamename, pageable);
    }

    public Optional<ScoreRecordEntity> findScoreRecordByGamenameAndUsername(String gamename, String username) {
        return scoreRecordRepository.findByGamenameAndUsernameAndDeletedFalse(gamename, username);
    }

    public Optional<ScoreRecordEntity> createScoreRecord(String gamename, String username, CreateScoreRecordRequest createScoreRecordRequest) {
        if (gameService.findGameByName(gamename).isEmpty()) {
            log.error("Game: {} not found while creating score-record.", gamename);
            return Optional.empty();
        }
        if (userService.findUserByUsername(username).isEmpty()) {
            log.error("User: {} not found while creating score-record.", username);
            return Optional.empty();
        }

        val optionalScoreRecordEntity = scoreRecordRepository.findByGamenameAndUsername(gamename, username);
        if (optionalScoreRecordEntity.isEmpty()) {
            val scoreRecordEntity = createScoreRecordEntity(gamename, username, createScoreRecordRequest);
            return Optional.of(scoreRecordRepository.save(scoreRecordEntity));
        }

        log.info("Updating, score-record already exists for Game: {} User: {}.", gamename, username);
        val scoreRecordEntity = optionalScoreRecordEntity.get();
        updateScoreRecordEntity(createScoreRecordRequest, scoreRecordEntity);
        return Optional.of(scoreRecordRepository.save(scoreRecordEntity));
    }

    public Optional<ScoreRecordEntity> deleteScoreRecord(String gamename, String username) {
        val optionalScoreRecordEntity = scoreRecordRepository.findByGamenameAndUsernameAndDeletedFalse(gamename, username);
        if (optionalScoreRecordEntity.isEmpty()) {
            log.error("Could not find score-record for deletion with Game: {} and User: {}.", gamename, username);
            return Optional.empty();
        }

        val scoreRecordEntity = optionalScoreRecordEntity.get();
        deleteScoreRecordEntity(scoreRecordEntity);
        return Optional.of(scoreRecordRepository.save(scoreRecordEntity));
    }

    private static ScoreRecordEntity createScoreRecordEntity(String gamename, String username, CreateScoreRecordRequest createScoreRecordRequest) {
        return ScoreRecordEntity.builder()
                .gamename(gamename)
                .username(username)
                .score(createScoreRecordRequest.getScore())
                .recordTime(createScoreRecordRequest.getRecordTime())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
    }

    private static void updateScoreRecordEntity(CreateScoreRecordRequest createScoreRecordRequest, ScoreRecordEntity scoreRecordEntity) {
        scoreRecordEntity.setScore(createScoreRecordRequest.getScore());
        scoreRecordEntity.setRecordTime(createScoreRecordRequest.getRecordTime());
        scoreRecordEntity.setDeleted(false);
        scoreRecordEntity.setUpdateTime(LocalDateTime.now());
    }

    private static void deleteScoreRecordEntity(ScoreRecordEntity scoreRecordEntity) {
        scoreRecordEntity.setDeleted(true);
        scoreRecordEntity.setUpdateTime(LocalDateTime.now());
    }
}
