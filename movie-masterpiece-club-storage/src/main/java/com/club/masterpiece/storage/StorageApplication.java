package com.club.masterpiece.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * https://www.baeldung.com/spring-data-disable-auto-config
 * 스프링 부트 자동 설정 제거
 * - JPA 의존성이 있는 COMMON.jar 를 임포트 하고있어서 dataSource config 문제가 있는 듯.
 * - 그래서 아래 내용만 자동으로 제외.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class, args);
    }
}
