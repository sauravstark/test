package com.example.leaderboard.service;

import com.example.leaderboard.model.game.CreateGameRequest;
import com.example.leaderboard.model.game.GameEntity;
import com.example.leaderboard.model.game.UpdateGameRequest;
import com.example.leaderboard.repository.GameRepository;
import com.example.leaderboard.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;

    private static GameEntity createGameEntity(CreateGameRequest createGameRequest) {
        return GameEntity.builder()
                .name(createGameRequest.getName())
                .displayName(createGameRequest.getDisplayName())
                .description(createGameRequest.getDescription())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
    }

    private static void updateGameEntity(UpdateGameRequest updateGameRequest, GameEntity gameEntity) {
        gameEntity.setDisplayName(updateGameRequest.getDisplayName());
        gameEntity.setDescription(updateGameRequest.getDescription());
        gameEntity.setUpdateTime(LocalDateTime.now());
    }

    private static void deleteGameEntity(GameEntity gameEntity) {
        gameEntity.setDeleted(true);
        gameEntity.setUpdateTime(LocalDateTime.now());
    }

    public List<GameEntity> findGamesByPage(int page, int count) {
        val pageable = Utils.pageableFromPageAndCount(page, count);
        return gameRepository.findByDeletedFalse(pageable).toList();
    }

    public Optional<GameEntity> findGameByName(String gamename) {
        return gameRepository.findByNameAndDeletedFalse(gamename);
    }

    public GameEntity createGame(CreateGameRequest createGameRequest) {
        val gameEntity = createGameEntity(createGameRequest);
        return gameRepository.save(gameEntity);
    }

    public Optional<GameEntity> updateGame(String gamename, UpdateGameRequest updateGameRequest) {
        val optionalGameEntity = gameRepository.findByNameAndDeletedFalse(gamename);
        if (optionalGameEntity.isEmpty()) {
            return Optional.empty();
        }
        val gameEntity = optionalGameEntity.get();
        updateGameEntity(updateGameRequest, gameEntity);
        return Optional.of(gameRepository.save(gameEntity));
    }

    public Optional<GameEntity> deleteGame(String gamename) {
        val optionalGameEntity = gameRepository.findByNameAndDeletedFalse(gamename);
        if (optionalGameEntity.isEmpty()) {
            return Optional.empty();
        }
        val gameEntity = optionalGameEntity.get();
        deleteGameEntity(gameEntity);
        return Optional.of(gameRepository.save(gameEntity));
    }

}
