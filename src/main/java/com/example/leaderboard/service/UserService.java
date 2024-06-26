package com.example.leaderboard.service;

import com.example.leaderboard.model.user.CreateUserRequest;
import com.example.leaderboard.model.user.UserEntity;
import com.example.leaderboard.model.user.UpdateUserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findUsersByPage(int page, int count);
    Optional<UserEntity> findUserByUsername(String username);
    UserEntity createUser(CreateUserRequest createUserRequest);
    Optional<UserEntity> updateUser(String username, UpdateUserRequest updateUserRequest);
    Optional<UserEntity> deleteUser(String username);
}
