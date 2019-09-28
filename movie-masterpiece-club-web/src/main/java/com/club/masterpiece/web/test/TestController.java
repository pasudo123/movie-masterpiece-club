package com.club.masterpiece.web.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-09-25
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("test-api")
@Slf4j
public class TestController {

    @GetMapping
    public ResponseEntity<TestDto.Response> getTestDto(@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") @RequestParam(value = "startDate", required = false) LocalDateTime startDate,
                                                       @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") @RequestParam(value = "endDate", required = false) LocalDateTime endDate) {

        log.debug("Get Mapping - startDate : {}", startDate);
        log.debug("Get Mapping - endDate : {}", endDate);

        return ResponseEntity.ok()
                .body(TestDto.Response.builder()
                .localDateTime(LocalDateTime.now())
                .build());

    }

    @PostMapping
    public ResponseEntity<TestDto.Response> postTestDto(@RequestBody TestDto.Request request){

        log.debug("Post Mapping - startDate : {}", request.getStartDate());
        log.debug("Post Mapping - endDate : {}", request.getEndDate());

        return ResponseEntity.ok()
                .body(TestDto.Response.builder()
                .localDateTime(LocalDateTime.now())
                .build());
    }
}
