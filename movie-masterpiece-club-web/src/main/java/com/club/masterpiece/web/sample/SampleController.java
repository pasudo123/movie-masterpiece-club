package com.club.masterpiece.web.sample;

import org.springframework.web.bind.annotation.*;

/**
 * Created by pasudo123 on 2019-10-26
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/sample")
public class SampleController {

    @PostMapping
    public String post(){
        return "post";
    }

    @GetMapping("{id}")
    public String get() {
        return "get";
    }

    @PutMapping("{id}")
    public String put() {
        return "put";
    }

    @DeleteMapping("{id}")
    public String delete() {
        return "delete";
    }
}
