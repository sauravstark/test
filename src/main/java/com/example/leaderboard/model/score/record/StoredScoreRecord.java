package com.example.leaderboard.model.score.record;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "score_records",
        indexes = {@Index(name = "idx_gameId_score", columnList = "gameId, score DESC")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"gameId", "userId"})}
)
public class StoredScoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long gameId;
    @Column(nullable = false)
    private Long score;
    private LocalDateTime recordTime;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}
