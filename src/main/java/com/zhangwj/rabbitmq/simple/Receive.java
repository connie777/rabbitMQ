package com.zhangwj.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.zhangwj.rabbitmq.util.ConnectionUtil;

/**
 * @ClassName Receive
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/17 15:42
 * @Version 1.0
 **/
public class Receive {
    private final static String QUEUE_NAME = "queue_01";

    public static void main(String[] argv) throws Exception {
        Connection connection=ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //同一时刻服务器只会发送一条消息给消费者，当消息处理完毕，有了反馈，才会进行第二次发送
        channel.basicQos(1);

        QueueingConsumer consumer=new QueueingConsumer(channel);

        //手动确认
        channel.basicConsume(QUEUE_NAME,false,consumer);

        while (true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message=new String(delivery.getBody());
            System.out.println("[receive1]:"+message);
            Thread.sleep(10);
            //手动确认消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }



















    }
}
