package com.club.masterpiece.web.config.security;

import com.club.masterpiece.web.user.model.User;
import com.club.masterpiece.web.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Transactional
public class SecurityOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oauth2User = delegate.loadUser(userRequest);

        String attributeKey = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        CustomOAuthAttributes attributes = CustomOAuthAttributes.of(registrationId, oauth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        return SecurityOAuth2User.builder()
                .user(user)
                .authorities(Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString())))
                .attributes(attributes.getAttributes())
                .attributeKey(attributeKey)
                .build();
    }

    private User saveOrUpdate(CustomOAuthAttributes attributes) {

        Optional<User> optional = userRepository.findOneById(attributes.getUserRegistrationId());
        User currentUser = null;

        if(optional.isPresent()) {
            currentUser = optional.get();
            currentUser.reLogin();
        } else {
            currentUser = attributes.toEntity();
        }

        return userRepository.save(currentUser);

    }
}
