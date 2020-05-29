package com.club.masterpiece.storage.image.controller;

import com.club.masterpiece.storage.image.dto.ImageDto;
import com.club.masterpiece.storage.image.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/static")
@RequiredArgsConstructor
public class ImageUploadController {

    private final ImageUploadService imageUploadService;

    @PostMapping("image")
    public ResponseEntity<ImageDto.CreateResponse> handleImageUpload(@RequestBody ImageDto.CreateRequest createRequest){
        return ResponseEntity.ok().body(imageUploadService.upload(createRequest));
    }
}
