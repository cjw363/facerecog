package com.facerecog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Classname FaceRecogServiceApplication
 * @Description
 * @Date 2019/7/15 20:24
 * @Created by cjw
 */
@SpringBootApplication
@EnableCaching //开启缓存机制
public class FaceRecogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FaceRecogServiceApplication.class, args);
    }
}
