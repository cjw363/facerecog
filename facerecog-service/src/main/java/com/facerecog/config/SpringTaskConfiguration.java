package com.facerecog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Description 定时任务配置
 * @Date 2020/8/3 21:03
 * @Created by cjw
 */
@Configuration
@EnableScheduling
public class SpringTaskConfiguration {
    /**
     * 不能同时使用websocket和spring的定时注解,会冲突。所以自定义一个TaskScheduler
     * @return
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduling = new ThreadPoolTaskScheduler();
        scheduling.setPoolSize(10);
        scheduling.initialize();
        return scheduling;
    }
}
