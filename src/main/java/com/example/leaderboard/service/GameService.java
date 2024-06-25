package com.example.leaderboard.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lombok.val;
import org.springframework.stereotype.Service;

import com.example.leaderboard.model.game.CreateGameRequest;
import com.example.leaderboard.model.game.StoredGame;
import com.example.leaderboard.model.game.UpdateGameRequest;
import com.example.leaderboard.repository.GameRepository;
import com.example.leaderboard.utils.Utils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameService {
    private GameRepository gameRepository;

    public List<StoredGame> getAllGames(int page, int count) {
        val pageable = Utils.pageableFromPageAndCount(page, count);
        return gameRepository.findByDeletedFalse(pageable).toList();
    }

    public Optional<StoredGame> findGame(String gamename) {
        return gameRepository.findByNameAndDeletedFalse(gamename);
    }

    public StoredGame createGame(CreateGameRequest createGameRequest) {
        val storedGame = StoredGame.builder()
        .name(createGameRequest.getName())
        .displayName(createGameRequest.getDisplayName())
        .description(createGameRequest.getDescription())
        .createTime(LocalDateTime.now())
        .updateTime(LocalDateTime.now())
        .deleted(false)    
        .build();
        return gameRepository.save(storedGame);
    }

    public Optional<StoredGame> updateGame(String gamename, UpdateGameRequest updateGameRequest) {
        val optionalStoredGame = gameRepository.findByNameAndDeletedFalse(gamename);
        if (optionalStoredGame.isEmpty()) {
            return Optional.empty();
        }
        val storedGame = optionalStoredGame.get();
        storedGame.setDisplayName(updateGameRequest.getDisplayName());
        storedGame.setDescription(updateGameRequest.getDescription());
        storedGame.setUpdateTime(LocalDateTime.now());
        return Optional.of(gameRepository.save(storedGame));
    }

    public Optional<StoredGame> deleteGame(String name) {
        val optionalStoredGame = gameRepository.findByNameAndDeletedFalse(name);
        if (optionalStoredGame.isEmpty()) {
            return Optional.empty();
        }
        val storedGame = optionalStoredGame.get();
        storedGame.setDeleted(true);
        storedGame.setUpdateTime(LocalDateTime.now());
        return Optional.of(gameRepository.save(storedGame));
    }
}
