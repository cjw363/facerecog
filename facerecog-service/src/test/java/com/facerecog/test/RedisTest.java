package com.facerecog.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Classname RedisTest
 * @Description
 * @Date 2019/9/9 14:37
 * @Created by cjw
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        // 将code存入redis
        this.redisTemplate.opsForValue().set("key_test", "this is a redis test msg", 5, TimeUnit.MINUTES);
    }
}
