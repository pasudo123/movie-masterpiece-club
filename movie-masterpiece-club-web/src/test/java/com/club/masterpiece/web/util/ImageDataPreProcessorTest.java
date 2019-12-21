package com.club.masterpiece.web.util;

import com.club.masterpiece.common.attachment.dto.ImageExtractElement;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by pasudo123 on 2019-12-15
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        ImageDataPreProcessor.class,
        ObjectMapper.class
})
public class ImageDataPreProcessorTest {

    @Autowired
    private ImageDataPreProcessor imageDataPreProcessor;

    @Test
    public void _extractTest() {

        String image = "<img src=\"data:image/png;base64,0qBBk0MCNAAAWQI0AEBAsXgiXdbc9pp==\" style=\"\" width=\"444\">";

        // given & when
        List<ImageExtractElement> elements = imageDataPreProcessor.extract(image);
        ImageExtractElement element = elements.get(0);

        // then
        assertThat(element.getImageSrc()).isEqualTo("0qBBk0MCNAAAWQI0AEBAsXgiXdbc9pp==");
        assertThat(element.getStyle()).isEqualTo("");
        assertThat(element.getWidth()).isEqualTo("444");
    }
}