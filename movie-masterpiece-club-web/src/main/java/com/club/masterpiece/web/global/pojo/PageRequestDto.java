package com.club.masterpiece.web.global.pojo;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


/**
 * Created by pasudo123 on 2019-11-03
 * Email: oraedoa@gmail.com
 **/
@Getter
public class PageRequestDto {

    private int page;
    private int size;
    private Sort.Direction direction;

    public void setPage(int page) {
        /** 0 보다 작은 페이지가 들어올 시, 1 으로 설정. **/
        this.page = (page <= 0) ? 1 : page;
    }

    public void setSize(int size) {
        /** 요청 사이즈 50보다 크면, 기본사이즈 10으로 바인딩. **/
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        this.size = (size > MAX_SIZE) ? DEFAULT_SIZE : size;
    }

    public void setDirection(Sort.Direction direction) {
        this.direction = direction;
    }

    public PageRequest of(){
        /** 페이지는 0 부터 시작한다. **/
        return PageRequest.of(page - 1, size, direction, "regDate");
    }
}
