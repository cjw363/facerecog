package com.facerecog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname FaceRecogServiceApplication
 * @Description
 * @Date 2019/7/15 20:24
 * @Created by cjw
 */
@SpringBootApplication
@MapperScan("com.facerecog.dao")
public class FaceRecogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FaceRecogServiceApplication.class, args);
    }
}
