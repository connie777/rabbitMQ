package com.zhangwj.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zhangwj.rabbitmq.util.ConnectionUtil;

/**
 * @ClassName Producer
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/19 15:08
 * @Version 1.0
 **/
public class Producer {
    private final static String EXCHANGE_NAME="test_exchange_direct";

    public static void main(String[] args) throws Exception {
        //获取连接及mq通道
        Connection connection= ConnectionUtil.getConnection();
        Channel channel=connection.createChannel();

        //声明exchange,名称，模式
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //消息内容
        String message="删除商品";
        //delete为消息的key
        channel.basicPublish(EXCHANGE_NAME,"delete",null,message.getBytes());

        message="insert goods";
        channel.basicPublish(EXCHANGE_NAME,"insert",null,message.getBytes());

        message="update goods";
        channel.basicPublish(EXCHANGE_NAME,"update",null,message.getBytes());

        channel.close();
        connection.close();
    }
}
