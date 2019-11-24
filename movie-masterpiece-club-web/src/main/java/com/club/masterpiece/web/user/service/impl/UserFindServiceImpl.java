package com.club.masterpiece.web.user.service.impl;

import com.club.masterpiece.common.user.model.User;
import com.club.masterpiece.common.user.repository.UserRepository;
import com.club.masterpiece.web.exception.EmptyResultException;
import com.club.masterpiece.web.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class UserFindServiceImpl implements UserFindService {

    private final UserRepository userRepository;

    @Override
    public User findOneById(String id) {

        return userRepository.findOneById(id).orElseThrow(() -> new EmptyResultException("User Not Found."));
    }
}