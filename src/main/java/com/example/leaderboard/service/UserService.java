package com.example.leaderboard.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.val;
import org.springframework.stereotype.Service;

import com.example.leaderboard.model.user.CreateUserRequest;
import com.example.leaderboard.model.user.StoredUser;
import com.example.leaderboard.model.user.UpdateUserRequest;
import com.example.leaderboard.repository.UserRepository;
import com.example.leaderboard.utils.Utils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<StoredUser> getAllUsers(int page, int count) {
        val pageable = Utils.pageableFromPageAndCount(page, count);
        return userRepository.findByDeletedFalse(pageable)
            .toList();
    }

    public Optional<StoredUser> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<StoredUser> findUser(String username) {
        return userRepository.findByUsernameAndDeletedFalse(username);
    }

    public StoredUser createUser(CreateUserRequest createUserRequest) {
        val storedUser = StoredUser.builder()
            .username(createUserRequest.getUsername())
            .name(createUserRequest.getName())
            .email(createUserRequest.getEmail())
            .dob(createUserRequest.getDob())
            .createTime(LocalDateTime.now())
            .updateTime(LocalDateTime.now())
            .deleted(false)
            .build();
        return userRepository.save(storedUser);
    }

    public Optional<StoredUser> updateUser(String username, UpdateUserRequest updateUserRequest) {
        val optionalStoredUser = userRepository.findByUsernameAndDeletedFalse(username);
        if (optionalStoredUser.isEmpty()) {
            return Optional.empty();
        }
        val storedUser = optionalStoredUser.get();
        storedUser.setName(updateUserRequest.getName());
        storedUser.setEmail(updateUserRequest.getEmail());
        storedUser.setDob(updateUserRequest.getDob());
        storedUser.setUpdateTime(LocalDateTime.now());
        return Optional.of(userRepository.save(storedUser));
    }

    public Optional<StoredUser> deleteUser(String username) {
        val optionalStoredUser = userRepository.findByUsernameAndDeletedFalse(username);
        if (optionalStoredUser.isEmpty()) {
            return Optional.empty();
        }
        val storedUser = optionalStoredUser.get();
        storedUser.setDeleted(true);
        storedUser.setUpdateTime(LocalDateTime.now());
        return Optional.of(userRepository.save(storedUser));
    }
}
