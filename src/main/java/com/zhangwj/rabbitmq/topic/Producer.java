package com.zhangwj.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhangwj.rabbitmq.util.ConnectionUtil;

/**
 * @ClassName Producer
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/19 16:48
 * @Version 1.0
 **/
public class Producer {
    //交换机名字
    private final static String EXCHANGE_NAME="test_exchange_topic";

    public static void main(String[] args) throws Exception {
        //获取连接及mq通道
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();

        //声明交换机,模式为topic
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        //消息内容
        String message="Hello RabbitMq";
        channel.basicPublish(EXCHANGE_NAME,"routekey.1",null,message.getBytes());

        channel.close();
        connection.close();














    }
}
