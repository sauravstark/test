package com.example.leaderboard.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leaderboard.model.game.StoredGame;

public interface GameRepository extends JpaRepository<StoredGame, Long> {
    Page<StoredGame> findByDeletedFalse(Pageable pageable);
    Optional<StoredGame> findByNameAndDeletedFalse(String name);
}
