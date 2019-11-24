package com.club.masterpiece.web.config.security;

import com.club.masterpiece.common.user.model.User;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class SecurityOAuth2User implements OAuth2User, Serializable {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    private final User user;
    private final Set<GrantedAuthority> authorities;
    private final Map<String, Object> attributes;
    private final String attributeKey;

    @Builder
    public SecurityOAuth2User(User user, Set<GrantedAuthority> authorities, Map<String, Object> attributes, String attributeKey) {
        this.user = user;
        this.authorities = authorities;
        this.attributes = attributes;
        this.attributeKey = attributeKey;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public String getName() {
        return this.user.getUsername();
    }

    public User getUser() {
        return this.user;
    }
}
