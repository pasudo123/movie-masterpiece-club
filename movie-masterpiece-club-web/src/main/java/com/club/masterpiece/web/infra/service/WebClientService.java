package com.club.masterpiece.web.infra.service;

import com.club.masterpiece.common.attachment.dto.ImageDto;
import com.club.masterpiece.web.pojo.CustomImageData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebClientService {

    @Value("${external.image-upload-url}")
    private String imageUploadUrl;
    private final WebClient.Builder webClientBuilder;

    public List<ImageDto.CreateResponse> uploadImages(final List<CustomImageData> imageExtractElementList){
        final List<ImageDto.CreateResponse> responses = new ArrayList<>();

        for(CustomImageData element : imageExtractElementList){
            final ImageDto.CreateResponse response = webClientBuilder.build()
                    .post()
                    .uri(imageUploadUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(new ImageDto.CreateRequest(element.getSrc())), ImageDto.CreateRequest.class)
                    .retrieve()
                    .bodyToMono(ImageDto.CreateResponse.class)
                    .block();

            responses.add(response);
        }

        return responses;
    }

    public Mono<ImageDto.CreateResponse> uploadImage(final ImageDto.CreateRequest createRequest){
        return webClientBuilder.build()
                .post()
                .uri(imageUploadUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(createRequest), ImageDto.CreateRequest.class)
                .retrieve()
                .bodyToMono(ImageDto.CreateResponse.class);
    }
}
