package com.example.leaderboard.controller;

import java.util.List;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.leaderboard.model.game.CreateGameRequest;
import com.example.leaderboard.model.game.GameDto;
import com.example.leaderboard.model.game.UpdateGameRequest;
import com.example.leaderboard.service.GameService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/games")
@AllArgsConstructor
public class GameController {
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames(@RequestParam int page,
                                                     @RequestParam int count) {
        val gameDtoList = gameService.findGamesByPage(page, count).stream()
                .map(GameDto::fromGameEntity)
                .toList();
        return ResponseEntity.ok(gameDtoList);
    }

    @GetMapping("/{gamename}")
    public ResponseEntity<GameDto> getGameByName(String gamename) {
        val optionalGameDto = gameService.findGameByName(gamename)
                .map(GameDto::fromGameEntity);
        return ResponseEntity.of(optionalGameDto);
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody CreateGameRequest createGameRequest) {
        val gameDto = GameDto.fromGameEntity(gameService.createGame(createGameRequest));
        return ResponseEntity.ok(gameDto);
    }

    @PutMapping("/{gamename}")
    public ResponseEntity<GameDto> updateGame(@PathVariable String gamename,
                                              @RequestBody UpdateGameRequest updateGameRequest) {
        val optionalGameDto = gameService.updateGame(gamename, updateGameRequest)
                .map(GameDto::fromGameEntity);
        return ResponseEntity.of(optionalGameDto);
    }

    @DeleteMapping("/{gamename}")
    public ResponseEntity<Void> deleteGame(@PathVariable String gamename) {
        val optionalGameEntity = gameService.deleteGame(gamename);
        if (optionalGameEntity.isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
