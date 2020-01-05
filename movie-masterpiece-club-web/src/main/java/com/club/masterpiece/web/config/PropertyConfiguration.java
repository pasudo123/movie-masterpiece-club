package com.club.masterpiece.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

/**
 * Created by pasudo123 on 2020-01-05
 * Email: oraedoa@gmail.com
 **/
@Configuration
public class PropertyConfiguration {

    @Bean
    @Profile("prod")
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new FileSystemResource("/data/etc/application-prod.yml"));

        /** 리소스 파일이 미존재시 건너뛰기 설정을 `false` 로 한다. : 미존재시 에러 발생 **/
        properties.setIgnoreResourceNotFound(false);

        return properties;
    }
}