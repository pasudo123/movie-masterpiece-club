package com.club.masterpiece.web.util;

import java.util.UUID;

/**
 * Created by pasudo123 on 2019-10-13
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
public class CommentIdGenerator {

    public String generateId() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
