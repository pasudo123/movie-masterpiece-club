package com.club.masterpiece.web.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by pasudo123 on 2019-09-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleIdGeneratorTest {

    @Test
    public void 아티클_아이디를_생성한다() {

        String str1 = "홍길동";
        System.out.println(UUID.nameUUIDFromBytes(str1.getBytes()));

        String str2 = "홍길돌";
        System.out.println(UUID.nameUUIDFromBytes(str2.getBytes()));

        String str3 = "홍길동";
        System.out.println(UUID.nameUUIDFromBytes(str3.getBytes()));

        String timeStr = LocalDateTime.now().toString();
        System.out.println(timeStr);
        System.out.println(UUID.nameUUIDFromBytes(timeStr.getBytes()));
    }
}