package com.club.masterpiece.web.auth.api;

import com.club.masterpiece.common.auth.dto.AuthDto;
import com.club.masterpiece.web.config.security.SecurityOAuth2User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pasudo123 on 2019-10-12
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping
    public ResponseEntity<AuthDto.CurrentAuth> getCurrentUser(@AuthenticationPrincipal SecurityOAuth2User user) {

        AuthDto.CurrentAuth currentAuth = new AuthDto.CurrentAuth(user.getUser());

        return ResponseEntity.ok().body(currentAuth);
    }
}