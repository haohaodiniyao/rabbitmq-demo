package com.example.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerDemo1 {
    private final static String QUEUE_NAME = "my-queue";
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("myVirtualHost");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        connectionFactory.setSharedExecutor(executorService);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        System.out.println("waiting messages ...");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("received message -> "+message);
            }
        };
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
