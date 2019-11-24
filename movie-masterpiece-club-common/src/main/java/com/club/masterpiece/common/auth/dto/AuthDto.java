package com.club.masterpiece.common.auth.dto;

import com.club.masterpiece.common.user.model.Role;
import com.club.masterpiece.common.user.model.User;
import lombok.Getter;

/**
 * Created by pasudo123 on 2019-10-12
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class AuthDto {

    @Getter
    public static class CurrentAuth {

        private String id;
        private String username;
        private String email;
        private String profile;
        private String desc;
        private Role role;

        public CurrentAuth(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.profile = user.getProfile();
            this.desc = user.getUDescription();
            this.role = user.getRole();
        }

    }
}
