package com.club.masterpiece.web.sample;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pasudo123 on 2019-10-26
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@RestController
@RequestMapping("/sample")
public class SampleController {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String post(){
        return "post";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public String get() {
        return "get";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public String put() {
        return "put";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public String delete() {
        return "delete";
    }
}
