package com.club.masterpiece.web.util.image;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ImageDataUtil.class)
@DisplayName("이미지 데이터 유틸리티 클래스는")
public class ImageDataUtilTest {

    @Autowired
    private ImageDataUtil imageDataUtil;

    @Test
    public void changeImageSrcIfPossibleTest() {
        imageDataUtil.changeImageSrcIfPossible("<p>hello</p>", null);
    }

    @Test
    public void extractImage() {
    }
}