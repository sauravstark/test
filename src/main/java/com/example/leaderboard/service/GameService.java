package com.example.leaderboard.service;

import com.example.leaderboard.model.game.CreateGameRequest;
import com.example.leaderboard.model.game.GameEntity;
import com.example.leaderboard.model.game.UpdateGameRequest;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<GameEntity> findGamesByPage(int page, int count);
    Optional<GameEntity> findGameByName(String gamename);
    GameEntity createGame(CreateGameRequest createGameRequest);
    Optional<GameEntity> updateGame(String gamename, UpdateGameRequest updateGameRequest);
    Optional<GameEntity> deleteGame(String name);
}
