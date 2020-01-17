package com.zhangwj.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhangwj.rabbitmq.util.ConnectionUtil;

/**
 * @ClassName Send
 * @Description TODO producer send message to message queue
 * @Author Silence
 * @Date 2020/1/17 15:28
 * @Version 1.0
 **/
public class Send {
    private final static String QUEUE_NAME = "queue_01";

    public static void main(String[] argv) throws Exception {
        Connection connection=ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        for (int i = 0; i < 100; i++) {
            String message=""+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            Thread.sleep(i*10);
        }

        channel.close();
        connection.close();

    }
}
