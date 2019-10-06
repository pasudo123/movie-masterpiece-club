package com.club.masterpiece.web.user.dto;

import com.club.masterpiece.web.config.security.SecurityOAuth2User;
import com.club.masterpiece.web.user.model.Role;
import com.club.masterpiece.web.user.model.User;
import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * Created by pasudo123 on 2019-10-06
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class UserDto {

    @Getter
    public static class CurrentUser {

        private String username;
        private String email;
        private String profile;
        private String desc;
        private Role role;

        public CurrentUser(User user) {
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.profile = user.getProfile();
            this.desc = user.getUDescription();
            this.role = user.getRole();
        }
    }

}
