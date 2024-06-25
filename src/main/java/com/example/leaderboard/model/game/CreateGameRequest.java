package com.example.leaderboard.model.game;

import lombok.Data;

@Data
public class CreateGameRequest {
    private String name;
    private String displayName;
    private String description;
}
