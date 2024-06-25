package com.example.leaderboard.model.leaderboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserScoreDto {
    private String username;
    private String name;
    private String email;
    private LocalDate dob;
    private Long score;
    private LocalDateTime recordTime;
}
