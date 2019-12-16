package com.club.masterpiece.web.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by pasudo123 on 2019-12-15
 * Email: oraedoa@gmail.com
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ImageDataExtractor.class)
public class ImageDataExtractorTest {

    @Autowired
    private ImageDataExtractor imageDataExtractor;

    @Test
    public void _extractTest() {

        String image = "<p>1111111111</p><p><img src=\"data:image/png;base64,0qBBk0MCNAAAWQI0AEBAsXgiXdbc9pp==\" style=\"\" width=\"444\"></p><p>22222222</p><p><img src=\"data:image/png;base64,iVBSUVORK5CYII=\" style=\"cursor: nesw-resize;\" width=\"450\"></p>";

        imageDataExtractor.extract(image);
    }

}
