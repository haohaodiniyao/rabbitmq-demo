package com.example.rabbitmq.publishsubscribe.fanout;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
    private static final String EXCHANGE_NAME="logs";
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        String msg = "simple queue message";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes("UTF-8"));
        channel.close();
        connection.close();
    }
}
