package com.zhangwj.rabbitmq.subscribe;

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
public class Receive2 {
    private final static String QUEUE_NAME = "queue_work_02";
    private final static String EXCHANGE_NAME="exchange_01";

    public static void main(String[] argv) throws Exception {
        //获取连接以及mq通道
        Connection connection=ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");

        //同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);

        //定义队列的消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);
        //监听队列，开启手动消息确认
        channel.basicConsume(QUEUE_NAME,false,consumer);

        //获取消息
        while(true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message=new String(delivery.getBody());
            System.out.println("Receive【2】:"+message);
            Thread.sleep(10);

            //手动确认消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }
}
