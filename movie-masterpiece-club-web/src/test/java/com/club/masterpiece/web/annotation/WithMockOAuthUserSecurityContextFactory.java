package com.club.masterpiece.web.annotation;

import com.club.masterpiece.common.user.model.Role;
import com.club.masterpiece.common.user.model.User;
import com.club.masterpiece.web.config.security.SecurityOAuth2User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WithMockOAuthUserSecurityContextFactory implements WithSecurityContextFactory<WithMockOAuthUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockOAuthUser withMockOAuthUser) {

        Map<String, Object> attributes = new HashMap<String, Object>(){{
            put("sub", "empty_sub");
            put("name", "empty_name");
            put("given_name", "empty_given_name");
            put("picture", "empty_picture");
            put("email", "empty_email");
            put("email_verified", true);
            put("locale", "ko");
        }};

        User user = User.builder()
                .id(withMockOAuthUser.id())
                .username(withMockOAuthUser.username())
                .email(withMockOAuthUser.email())
                .profile(withMockOAuthUser.profile())
                .build();

        UserDetails oAuth2User = SecurityOAuth2User.builder()
                .user(user)
                .attributes(attributes)
                .authorities(Collections.singleton(new SimpleGrantedAuthority(withMockOAuthUser.roles()[0])))
                .attributeKey("sub")
                .build();

        Authentication authentication = new UsernamePasswordAuthenticationToken(oAuth2User, withMockOAuthUser.password(), oAuth2User.getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        return context;
    }
}
