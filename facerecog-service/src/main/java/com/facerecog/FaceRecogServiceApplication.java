package com.facerecog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname FaceRecogServiceApplication
 * @Description
 * @Date 2019/7/15 20:24
 * @Created by cjw
 */
@SpringBootApplication
@MapperScan("com.facerecog.dao")
@EnableCaching
public class FaceRecogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FaceRecogServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
