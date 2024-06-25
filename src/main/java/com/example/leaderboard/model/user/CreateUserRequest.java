package com.example.leaderboard.model.user;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String name;
    private String email;
    private LocalDate dob;
}
