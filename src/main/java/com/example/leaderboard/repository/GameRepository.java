package com.example.leaderboard.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leaderboard.model.game.GameEntity;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
    Page<GameEntity> findByDeletedFalse(Pageable pageable);
    Optional<GameEntity> findByNameAndDeletedFalse(String name);
}
