package com.example.leaderboard.model.score.record;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
        indexes = {@Index(name = "idx_gamename_score", columnList = "gamename, score DESC")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"gamename", "username"})}
)
public class ScoreRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String gamename;
    @Column(nullable = false)
    private Long score;
    private LocalDateTime recordTime;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean deleted;
}
