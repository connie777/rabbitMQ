package com.zhangwj.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.zhangwj.rabbitmq.util.ConnectionUtil;

/**
 * @ClassName Consumer
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/19 16:53
 * @Version 1.0
 **/
public class Consumer2 {
    private final static String QUEUE_NAME="test_queue_topic_work_2";
    private final static String EXCHANGE_NAME="test_exchange_topic";

    public static void main(String[] args) throws Exception {
        //获取连接及mq通道
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //绑定交换机到队列
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"*.*");

        channel.basicQos(1);

        QueueingConsumer consumer=new QueueingConsumer(channel);

        channel.basicConsume(QUEUE_NAME,false,consumer);

        while (true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message=new String(delivery.getBody());
            System.out.println("Consumer[2]:"+message);
            Thread.sleep(10);
            //手动确认消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }













}
