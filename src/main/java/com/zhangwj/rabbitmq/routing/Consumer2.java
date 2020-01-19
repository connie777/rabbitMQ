package com.zhangwj.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.zhangwj.rabbitmq.util.ConnectionUtil;

/**
 * @ClassName Consumer
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/19 15:14
 * @Version 1.0
 **/
public class Consumer2 {
    //队列名称
    private final static String QUEUE_NAME="test_queue_direct_2";
    //交换机名称
    private final static String EXCHANGE_NAME="test_exchange_direct";

    public static void main(String[] args) throws Exception {
        //获取连接和mq通道
        Connection connection=ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"update");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"delete");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"insert");

        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        //定义队列的消费者
        QueueingConsumer consumer=new QueueingConsumer(channel);
        //监听队列，开启手动消息确认
        channel.basicConsume(QUEUE_NAME,false,consumer);

        //获取消息
        while(true){
            QueueingConsumer.Delivery delivery=consumer.nextDelivery();
            String message=new String(delivery.getBody());
            System.out.println("Consumer【2】:"+message);
            Thread.sleep(10);

            //手动确认消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }
}
