package com.club.masterpiece.web.user.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-09-22
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @Column(name = "id", length = 45, nullable = false)
    private String id;

    @Column(name = "password", length = 32, nullable = false)
    private String password;

    @Column(name = "username", length = 45)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('ADMIN', 'MASTER_USER', 'MANAGER_USER', 'GENERAL_USER')", nullable = false)
    private Role role;

    @Column(name = "email", length = 45)
    private String email;

    @Column(name = "profile", columnDefinition = "TEXT")
    private String profile;

    @Column(name = "u_description", columnDefinition = "TEXT")
    private String uDescription;

    @Column(name = "last_login_date", nullable = false)
    private LocalDateTime lastLoginDate;

    @CreatedDate
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "mod_date", nullable = false)
    private LocalDateTime modDate;

    @Builder
    public User(String id, String password, String username,
                Role role, String email, String profile, String uDescription,
                LocalDateTime lastLoginDate) {

        this.id = id;
        this.password = password;
        this.username = username;
        this.role = role;
        this.email = email;
        this.profile = profile;
        this.uDescription = uDescription;
        this.lastLoginDate = lastLoginDate;
    }

    public void reLogin() {
        this.lastLoginDate = LocalDateTime.now();
    }
}
