package com.facerecog.test;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @Classname RabbitMQTestRecv
 * @Description
 * @Date 2019/9/10 10:23
 * @Created by cjw
 */
//@Component
public class RabbitMQTestRecv {
    private static final String TEST_QUEUE = "facerecog.test.queue";
    private static final String TEST_QUEUE2 = "facerecog.test.queue2";
    private static final String TEST_EXCHANGE = "facerecog.test.exchange";

    @RabbitListener(bindings =
    @QueueBinding(value = @Queue(value = TEST_QUEUE,durable = "true"),
                  exchange = @Exchange(value = TEST_EXCHANGE,
                                       ignoreDeclarationExceptions = "true",
                                       type = ExchangeTypes.TOPIC),
                  key = {"topic_key1"}))
    public void listen1(String msg){
        System.out.println("listen1 收到消息了:"+msg);
    }

    @RabbitListener(bindings =
    @QueueBinding(value = @Queue(value = TEST_QUEUE,durable = "true"),
      exchange = @Exchange(value = TEST_EXCHANGE,
        ignoreDeclarationExceptions = "true",
        type = ExchangeTypes.FANOUT)))
    public void listen2(String msg){
        System.out.println("listen2 收到消息了:"+msg);
    }

    @RabbitListener(bindings =
    @QueueBinding(value = @Queue(value = TEST_QUEUE2,durable = "true"),
      exchange = @Exchange(value = TEST_EXCHANGE,
        ignoreDeclarationExceptions = "true",
        type = ExchangeTypes.FANOUT)))
    public void listen3(String msg){
        System.out.println("listen3 收到消息了:"+msg);
    }
}
