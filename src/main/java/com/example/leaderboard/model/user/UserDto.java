package com.example.leaderboard.model.user;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {
    private String username;
    private String name;
    private String email;
    private LocalDate dob;

    public static UserDto fromUserEntity(UserEntity userEntity) {
        return builder()
                .username(userEntity.getUsername())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .dob(userEntity.getDob())
                .build();
    }
}
