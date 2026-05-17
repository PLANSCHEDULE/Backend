package com.example.thirdproject.user.entity;

import com.example.thirdproject.global.entity.BaseTime;
import com.example.thirdproject.user.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTime {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false, updatable = false)
        private String uuid = UUID.randomUUID().toString();

        @Column(nullable = false)
        private String name;

        @Column(unique = true, nullable = false)
        private String email;

        @Column(nullable = false)
        private String password;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role role = Role.ROLE_USER;

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
