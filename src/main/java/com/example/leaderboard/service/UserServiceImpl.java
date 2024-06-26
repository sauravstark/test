package com.example.leaderboard.service;

import com.example.leaderboard.model.user.CreateUserRequest;
import com.example.leaderboard.model.user.UserEntity;
import com.example.leaderboard.model.user.UpdateUserRequest;
import com.example.leaderboard.repository.UserRepository;
import com.example.leaderboard.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private static UserEntity createUserEntity(CreateUserRequest createUserRequest) {
        return UserEntity.builder()
                .username(createUserRequest.getUsername())
                .name(createUserRequest.getName())
                .email(createUserRequest.getEmail())
                .dob(createUserRequest.getDob())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .deleted(false)
                .build();
    }

    private static void updateUserEntity(UpdateUserRequest updateUserRequest, UserEntity userEntity) {
        userEntity.setName(updateUserRequest.getName());
        userEntity.setEmail(updateUserRequest.getEmail());
        userEntity.setDob(updateUserRequest.getDob());
        userEntity.setUpdateTime(LocalDateTime.now());
    }

    private static void deleteUserEntity(UserEntity userEntity) {
        userEntity.setDeleted(true);
        userEntity.setUpdateTime(LocalDateTime.now());
    }

    public List<UserEntity> findUsersByPage(int page, int count) {
        val pageable = Utils.pageableFromPageAndCount(page, count);
        return userRepository.findByDeletedFalse(pageable).toList();
    }

    public Optional<UserEntity> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByUsernameAndDeletedFalse(username);
    }

    public UserEntity createUser(CreateUserRequest createUserRequest) {
        val userEntity = createUserEntity(createUserRequest);
        return userRepository.save(userEntity);
    }

    public Optional<UserEntity> updateUser(String username, UpdateUserRequest updateUserRequest) {
        val optionalUserEntity = userRepository.findByUsernameAndDeletedFalse(username);
        if (optionalUserEntity.isEmpty()) {
            return Optional.empty();
        }
        val userEntity = optionalUserEntity.get();
        updateUserEntity(updateUserRequest, userEntity);
        return Optional.of(userRepository.save(userEntity));
    }

    public Optional<UserEntity> deleteUser(String username) {
        val optionalUserEntity = userRepository.findByUsernameAndDeletedFalse(username);
        if (optionalUserEntity.isEmpty()) {
            return Optional.empty();
        }
        val userEntity = optionalUserEntity.get();
        deleteUserEntity(userEntity);
        return Optional.of(userRepository.save(userEntity));
    }
}
