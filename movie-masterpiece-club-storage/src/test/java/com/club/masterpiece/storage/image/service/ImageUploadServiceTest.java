package com.club.masterpiece.storage.image.service;

import com.club.masterpiece.storage.image.dto.ImageDto;
import com.club.masterpiece.storage.util.Base64Converter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(value = {SpringExtension.class})
@SpringBootTest(classes = {
        ImageUploadService.class,
        Base64Converter.class
})
@DisplayName("이미지 업로드 서비스 레이어는")
public class ImageUploadServiceTest {

    @Autowired
    private ImageUploadService imageUploadService;

    @ParameterizedTest()
    @MethodSource("provideImageCreateRequest")
    @DisplayName("이미지 업로드를 수행한다.")
    public void uploadTest(ImageDto.CreateRequest createRequest, String imageOriginUrl) {
        // given
        // when
        final ImageDto.CreateResponse createResponse = imageUploadService.upload(createRequest);

        // then
//        assertThat(createResponse.getImageOriginUrl()).isEqualTo(imageOriginUrl);
    }

    static Stream<Arguments> provideImageCreateRequest(){
        return Stream.of(
            Arguments.arguments(
                    new ImageDto.CreateRequest("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAB4AAAAQ4CAIAAABnsVYUAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAGbpSURBVHhe7P1nnF51nfj//++kJ3PV6b33PpPJ9LkmdBAEBUV67yWht3R0revasPeuKAoixd5w7YKKIr0lAUVIvlv0d+d/TRjYcAKZSXJ9AOX5fLwf7rLM55yTnOOd15z9nP9fHgAAAAAABCBAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQhAANAAAAAEAQAjQAAAAAAEEI0AAAAAAABCFAAwAAAAAQ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AHhFiCVSifzSZHFNqqQhXdqQKq5NFlTEU4X22QAAIBwBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKABAAAAAAhCgAYAAAAAIAgBGgAAAACAIARoAAAAAACCEKAB/v/t2MEAAAAAAyGD+eOO4n6FEQAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAQkADAAAAAJAQ0AAAAAAAJAQ0AAAAAAAJAQ0AAAAAQEJAAwAAAACQENAAAAAAACQENAAAAAAACQENAAAAAEBCQAMAAAAAkBDQAAAAAAAkBDQAAAAAAAkBDQAAAABAYDuzCK8IzWO1HwAAAABJRU5ErkJggg=="),
                    "https://masterpiece-1st.com/static/images/google.png"
            )
        );
    }
}