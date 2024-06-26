package com.example.leaderboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.leaderboard.model.score.record.ScoreRecordEntity;

public interface ScoreRecordRepository extends JpaRepository<ScoreRecordEntity, Long> {
    Page<ScoreRecordEntity> findByDeletedFalse(Pageable pageable);

    Optional<ScoreRecordEntity> findByGamenameAndUsername(String gamename, String username);
    Optional<ScoreRecordEntity> findByGamenameAndUsernameAndDeletedFalse(String gamename, String username);

    @Query("SELECT sr FROM ScoreRecordEntity sr WHERE sr.gamename = :gamename AND sr.deleted = false ORDER BY sr.score DESC")
    List<ScoreRecordEntity> findTopScoresByGamename(@Param("gamename") String gamename, Pageable pageable);
}
