package com.club.masterpiece.web.user.api;

import com.club.masterpiece.web.user.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pasudo123 on 2019-10-06
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/gulagbu-api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserFindService userFindService;

}
