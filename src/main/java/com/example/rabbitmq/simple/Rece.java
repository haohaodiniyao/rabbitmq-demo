package com.example.rabbitmq.simple;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Rece {
    private static final String QUEUE_NAME="simple-queue";
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                System.out.println("receive msg:"+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,consumer);
    }
}
