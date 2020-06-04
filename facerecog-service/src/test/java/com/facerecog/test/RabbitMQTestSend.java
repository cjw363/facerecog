package com.facerecog.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname RibbitmqTestSend
 * @Description
 * @Date 2019/9/10 10:21
 * @Created by cjw
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQTestSend {
    private static final String TEST_EXCHANGE = "facerecog.test.exchange";

    @Autowired
    private AmqpTemplate mAmqpTemplate;

    @Test
    public void sendMsg(){
        try {
            mAmqpTemplate.convertAndSend(TEST_EXCHANGE,"","hello");
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendCreateStaticPageMsg(){
        try {
            mAmqpTemplate.convertAndSend(TEST_EXCHANGE,"key_direct_static_page","1");
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }

}
