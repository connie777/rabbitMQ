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
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        String message="Hello rabbitMQ";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());

        channel.close();
        connection.close();
    }

















}
