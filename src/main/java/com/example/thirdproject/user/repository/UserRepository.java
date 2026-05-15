package com.example.thirdproject.user.repository;

import com.example.thirdproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    // uuid를 위한 메서드
    Optional<User>findByUuid(String uuid);
}
