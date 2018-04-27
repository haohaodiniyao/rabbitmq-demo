package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerDemo1 {
    private final static String QUEUE_NAME = "my-queue";
    private final static String VIRTUAL_HOST = "myVirtualHost";
    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost(VIRTUAL_HOST);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        connectionFactory.setSharedExecutor(executorService);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicPublish("",QUEUE_NAME,null,"my first rabbitmq message".getBytes("UTF-8"));
        channel.close();
        connection.close();
    }
}
