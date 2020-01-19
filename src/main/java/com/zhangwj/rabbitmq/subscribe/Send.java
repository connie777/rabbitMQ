package com.zhangwj.rabbitmq.subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhangwj.rabbitmq.util.ConnectionUtil;

/**
 * @ClassName Send
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/17 17:07
 * @Version 1.0
 **/
public class Send {
    private final static String EXCHANGE_NAME="exchange_01";

    public static void main(String[] args) throws Exception {
        //获取连接以及mq通道
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();
        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        String message="Hello rabbitMQ";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("message:"+message);
        channel.close();
        connection.close();
    }

















}
