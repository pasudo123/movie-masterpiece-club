package com.club.masterpiece.web.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by pasudo123 on 2019-11-24
 * Email: oraedoa@gmail.com
 **/
@Configuration
@EntityScan(value = {"com.club.masterpiece.common.*"})
@EnableJpaRepositories("com.club.masterpiece.common.*")
public class MultiModuleConfiguration {
}
