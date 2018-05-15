package com.example.rabbitmq.simple;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
    private static final String QUEUE_NAME="simple_queue_durable";
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        String msg = "simple queue message durable";
        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties();
        basicProperties.builder().deliveryMode()
        channel.basicPublish("",QUEUE_NAME,basicProperties,msg.getBytes("UTF-8"));
        channel.close();
        connection.close();
    }
}
