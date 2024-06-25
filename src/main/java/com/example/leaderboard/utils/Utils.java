package com.example.leaderboard.utils;

import com.example.leaderboard.model.game.GameDto;
import com.example.leaderboard.model.game.StoredGame;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Utils {
    private static int MAX_PAGE_SIZE = 100;
    public static Pageable pageableFromPageAndCount(int page, int count) {
        if (page < 0) {
            page = 0;
        }
        if (count <= 0 || count > MAX_PAGE_SIZE) {
            count = MAX_PAGE_SIZE;
        }
        return PageRequest.of(page, count);
    }

    public static GameDto gameDtoFromStoredGame(StoredGame storedGame) {
        return GameDto.builder()
                .name(storedGame.getName())
                .displayName(storedGame.getDisplayName())
                .description(storedGame.getDescription())
                .build();
    }
}
