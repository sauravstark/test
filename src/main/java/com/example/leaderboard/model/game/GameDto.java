package com.example.leaderboard.model.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class GameDto {
    private String name;
    private String displayName;
    private String description;

    public static GameDto fromGameEntity(GameEntity gameEntity) {
        return builder()
                .name(gameEntity.getName())
                .displayName(gameEntity.getDisplayName())
                .description(gameEntity.getDescription())
                .build();
    }
}
