package com.example.leaderboard.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leaderboard.model.user.StoredUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<StoredUser, Long> {
    Page<StoredUser> findByDeletedFalse(Pageable pageable);
    Optional<StoredUser> findByUsernameAndDeletedFalse(String username);

    @Query("SELECT u FROM StoredUser u WHERE u.id IN :ids AND u.deleted = false")
    List<StoredUser> findNonDeletedUsersByIds(@Param("ids") Set<Long> ids);
}
