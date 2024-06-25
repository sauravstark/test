package com.example.leaderboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.leaderboard.model.score.record.StoredScoreRecord;

public interface ScoreRecordRepository extends JpaRepository<StoredScoreRecord, Long> {
    Page<StoredScoreRecord> findByDeletedFalse(Pageable pageable);

    Optional<StoredScoreRecord> findByGameIdAndUserId(Long gameId, Long userId);
    Optional<StoredScoreRecord> findByGameIdAndUserIdAndDeletedFalse(Long gameId, Long userId);

    @Query("SELECT sr FROM StoredScoreRecord sr WHERE sr.gameId = :gameId AND sr.deleted = false ORDER BY sr.score DESC")
    List<StoredScoreRecord> findTopScoresByGameId(@Param("gameId") Long gameId, Pageable pageable);
}
