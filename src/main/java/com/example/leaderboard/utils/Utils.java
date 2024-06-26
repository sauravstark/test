package com.example.leaderboard.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Utils {
    public static Pageable pageableFromPageAndCount(int page, int count) {
        if (page < 0) {
            page = 0;
        }
        int MAX_PAGE_SIZE = 100;
        if (count <= 0 || count > MAX_PAGE_SIZE) {
            count = MAX_PAGE_SIZE;
        }
        return PageRequest.of(page, count);
    }
}
