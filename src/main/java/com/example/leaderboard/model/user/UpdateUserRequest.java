package com.example.leaderboard.model.user;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
    private LocalDate dob;
}
