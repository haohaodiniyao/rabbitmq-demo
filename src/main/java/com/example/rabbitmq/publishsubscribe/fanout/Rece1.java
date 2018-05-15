package com.example.rabbitmq.publishsubscribe.fanout;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Rece1 {
    private static final String QUEUE_NAME="info";
    private static final String EXCHANGE_NAME="logs";
    public static void main(String[] args) throws Exception{
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"UTF-8");
                System.out.println("receive 1 msg:"+msg);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
