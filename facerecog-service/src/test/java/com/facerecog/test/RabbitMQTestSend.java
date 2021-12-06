package com.facerecog.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

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

//    @Autowired
//    private AmqpTemplate mAmqpTemplate;
    @Autowired
    private RabbitTemplate mRabbitTemplate;

    @Test
    public void sendMsg(){
        try {
            /**
             * 如果消息没有到达交换机,则该方法中isSendSuccess = false,error为错误信息;
             * 如果消息正确到达交换机,则该方法中isSendSuccess = true;
             */
            mRabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
                @Override
                public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                    System.out.println("消息id:" + correlationData.getId());
                    if (ack) {
                        System.out.println("消息发送确认成功");
                    } else {
                        System.out.println("消息发送确认失败:" + cause);
                    }
                }
            });
            /**
             * 消息从交换机成功到达队列，则returnedMessage方法不会执行;
             * 消息从交换机未能成功到达队列，则returnedMessage方法会执行;
             */
            mRabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
                @Override
                public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                    try {
                        System.out.println("return--message:" + new String(message.getBody(), "UTF-8") + ",replyCode:" + replyCode
                          + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
                    } catch (UnsupportedEncodingException e) {
                    }
                }
            });
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            CorrelationData correlationData2 = new CorrelationData(UUID.randomUUID().toString());
            mRabbitTemplate.convertAndSend(TEST_EXCHANGE,"","hello1",correlationData);
            mRabbitTemplate.convertAndSend(TEST_EXCHANGE,"","hello2",correlationData2);
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendCreateStaticPageMsg(){
        try {
            mRabbitTemplate.convertAndSend(TEST_EXCHANGE,"key_direct_static_page","1");
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }

}
