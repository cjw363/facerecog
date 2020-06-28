package com.facerecog.test;

import com.rabbitmq.client.Channel;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Classname RabbitMQTestRecv
 * @Description
 * @Date 2019/9/10 10:23
 * @Created by cjw
 */
@Component
public class RabbitMQTestRecv {
    private static final String TEST_QUEUE = "facerecog.test.queue";
    private static final String TEST_QUEUE2 = "facerecog.test.queue2";
    private static final String TEST_EXCHANGE = "facerecog.test.exchange";

//    @RabbitListener(bindings =
//    @QueueBinding(value = @Queue(value = TEST_QUEUE,durable = "true"),
//                  exchange = @Exchange(value = TEST_EXCHANGE,
//                                       ignoreDeclarationExceptions = "true",
//                                       type = ExchangeTypes.TOPIC),
//                  key = {"topic_key1"}))
//    public void listen1(String msg){
//        System.out.println("listen1 收到消息了:"+msg);
//    }
//
//    @RabbitListener(bindings =
//    @QueueBinding(value = @Queue(value = TEST_QUEUE,durable = "true"),
//      exchange = @Exchange(value = TEST_EXCHANGE,
//        ignoreDeclarationExceptions = "true",
//        type = ExchangeTypes.FANOUT)))
//    public void listen2(String msg){
//        System.out.println("listen2 收到消息了:"+msg);
//    }
//
//    @RabbitListener(bindings =
//    @QueueBinding(value = @Queue(value = TEST_QUEUE2,durable = "true"),
//      exchange = @Exchange(value = TEST_EXCHANGE,
//        ignoreDeclarationExceptions = "true",
//        type = ExchangeTypes.FANOUT)))
//    public void listen3(String msg){
//        System.out.println("listen3 收到消息了:"+msg);
//    }

//    @RabbitListener(bindings =
//    @QueueBinding(value = @Queue(value = TEST_QUEUE,durable = "true"),
//      exchange = @Exchange(value = TEST_EXCHANGE,
//        ignoreDeclarationExceptions = "true",
//        type = ExchangeTypes.DIRECT),
//    key = {"key_direct_static_page"}))
//    public void listen4(String msg){
//        System.out.println("listen4 收到消息了:"+msg);
//    }

    @RabbitListener(queues = TEST_QUEUE)
    public void receiveMessage02(String msg, Channel channel, Message message) throws IOException {
        try {
            // 这里模拟一个空指针异常，
            String string = null;
            string.length();

            System.out.println("【Consumer02成功接收到消息】>>> {}"+ msg);
            // 确认收到消息，只确认当前消费者的一个消息收到
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                System.out.println("【Consumer02】消息已经回滚过，拒绝接收消息 ： {}"+ msg);
                // 拒绝消息，并且不再重新进入队列
                //public void basicReject(long deliveryTag, boolean requeue)
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                System.out.println("【Consumer02】消息即将返回队列重新处理 ：{}"+ msg);
                //设置消息重新回到队列处理
                // requeue表示是否重新回到队列，true重新入队
                //public void basicNack(long deliveryTag, boolean multiple, boolean requeue)
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            e.printStackTrace();
        }
    }
}
