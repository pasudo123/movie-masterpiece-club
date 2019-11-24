package com.club.masterpiece.web.config.security;

import com.club.masterpiece.common.user.model.Role;
import com.club.masterpiece.common.user.model.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Created by pasudo123 on 2019-09-30
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class CustomOAuthAttributes {

    private Map<String, Object> attributes;
    private String userRegistrationId;
    private String name;
    private String email;
    private String profileImage;

    @Builder
    public CustomOAuthAttributes(Map<String, Object> attributes, String userRegistrationId, String name, String email, String profileImage) {
        this.attributes = attributes;
        this.userRegistrationId = userRegistrationId;
        this.name = name;
        this.email = email;
        this.profileImage = profileImage;
    }

    static CustomOAuthAttributes of(String registrationId, Map<String, Object> attributes){

        String userRegistrationId = "";

        for(AuthType type : AuthType.values()){
            if(!type.name().equalsIgnoreCase(registrationId)){
                continue;
            }

            userRegistrationId = type.name().toUpperCase() + "_" + attributes.get("sub");
        }

        return CustomOAuthAttributes.builder()
                .attributes(attributes)
                .userRegistrationId(userRegistrationId)
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .profileImage((String) attributes.get("picture"))
                .build();
    }

    public User toEntity() {
        return User.builder()
                .id(userRegistrationId)
                .username(name)
                .email(email)
                .profile(profileImage)
                .role(Role.GENERAL_USER)
                .lastLoginDate(LocalDateTime.now())
                .build();
    }
}
