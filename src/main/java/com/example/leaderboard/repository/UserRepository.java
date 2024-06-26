package com.example.leaderboard.repository;

import java.util.Optional;

import com.example.leaderboard.model.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Page<UserEntity> findByDeletedFalse(Pageable pageable);
    Optional<UserEntity> findByUsernameAndDeletedFalse(String username);
}
